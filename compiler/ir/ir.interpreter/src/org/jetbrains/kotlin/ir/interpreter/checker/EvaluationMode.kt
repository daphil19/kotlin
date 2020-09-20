/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.interpreter.checker

import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.descriptors.IrBuiltIns
import org.jetbrains.kotlin.ir.expressions.*
import org.jetbrains.kotlin.ir.interpreter.builtins.compileTimeAnnotation
import org.jetbrains.kotlin.ir.interpreter.builtins.contractsDslAnnotation
import org.jetbrains.kotlin.ir.interpreter.builtins.evaluateIntrinsicAnnotation
import org.jetbrains.kotlin.ir.interpreter.hasAnnotation
import org.jetbrains.kotlin.ir.interpreter.isUnsigned
import org.jetbrains.kotlin.ir.types.isAny
import org.jetbrains.kotlin.ir.types.isPrimitiveType
import org.jetbrains.kotlin.ir.types.isString
import org.jetbrains.kotlin.ir.util.*
import org.jetbrains.kotlin.name.FqName

enum class EvaluationMode(protected val mustCheckBody: Boolean) {
    FULL(mustCheckBody = true) {
        override fun canEvaluateFunction(function: IrFunction, expression: IrCall?): Boolean {
            TODO("Not implemented")
        }
    },

    WITH_ANNOTATIONS(mustCheckBody = false) {
        override fun canEvaluateFunction(function: IrFunction, expression: IrCall?): Boolean {
            if (function.isCompileTimeProperty()) return true
            return function.isMarkedAsCompileTime() || function.origin == IrBuiltIns.BUILTIN_OPERATOR ||
                    (function is IrSimpleFunction && function.isOperator && function.name.asString() == "invoke") ||
                    (function is IrSimpleFunction && function.isFakeOverride && function.overriddenSymbols.any { canEvaluateFunction(it.owner) }) ||
                    function.isCompileTimeTypeAlias()
        }

        private fun IrDeclaration?.isCompileTimeProperty(): Boolean {
            val property = (this as? IrSimpleFunction)?.correspondingPropertySymbol?.owner ?: return false
            if (property.isConst) return true
            if (property.isMarkedAsCompileTime() || property.isCompileTimeTypeAlias()) return true

            val backingField = property.backingField
            val backingFieldExpression = backingField?.initializer?.expression as? IrGetValue
            return backingFieldExpression?.origin == IrStatementOrigin.INITIALIZE_PROPERTY_FROM_PARAMETER
        }
    },

    ONLY_BUILTINS(mustCheckBody = false) {
        override fun canEvaluateFunction(function: IrFunction, expression: IrCall?): Boolean {
            if ((function as? IrSimpleFunction)?.correspondingPropertySymbol?.owner?.isConst == true) return true

            val parent = function.parentClassOrNull ?: return false
            val parentType = parent.defaultType
            return when {
                parentType.isPrimitiveType() -> function.name.asString() !in setOf("inc", "dec", "rangeTo", "hashCode")
                parentType.isString() -> function.name.asString() !in setOf("subSequence", "hashCode")
                parentType.isAny() -> function.name.asString() == "toString" && expression?.dispatchReceiver !is IrGetObjectValue
                parent.isObject -> parent.parentClassOrNull?.defaultType?.let { it.isPrimitiveType() || it.isUnsigned() } == true
                else -> false
            }
        }
    };

    abstract fun canEvaluateFunction(function: IrFunction, expression: IrCall? = null): Boolean

    fun canEvaluateBody(function: IrFunction): Boolean {
        return (mustCheckBody || function.isLocal) && !function.isContract() && !function.isMarkedAsEvaluateIntrinsic()
    }

    protected val compileTimeTypeAliases = setOf(
        "java.lang.StringBuilder", "java.lang.IllegalArgumentException", "java.util.NoSuchElementException"
    )

    fun IrDeclaration.isMarkedAsCompileTime() = isMarkedWith(compileTimeAnnotation)
    private fun IrDeclaration.isContract() = isMarkedWith(contractsDslAnnotation)
    private fun IrDeclaration.isMarkedAsEvaluateIntrinsic() = isMarkedWith(evaluateIntrinsicAnnotation)
    protected fun IrDeclaration.isCompileTimeTypeAlias() = this.parentClassOrNull?.fqNameWhenAvailable?.asString() in compileTimeTypeAliases

    protected fun IrDeclaration.isMarkedWith(annotation: FqName): Boolean {
        if (this is IrClass && this.isCompanion) return false
        if (this.hasAnnotation(annotation)) return true
        return (this.parent as? IrClass)?.isMarkedWith(annotation) ?: false
    }
}
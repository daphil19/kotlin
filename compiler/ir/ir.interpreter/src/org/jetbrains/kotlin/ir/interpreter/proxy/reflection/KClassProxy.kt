/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.interpreter.proxy.reflection

import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.ir.interpreter.IrInterpreter
import org.jetbrains.kotlin.ir.interpreter.internalName
import org.jetbrains.kotlin.ir.interpreter.proxy.Proxy
import org.jetbrains.kotlin.ir.interpreter.state.KClassState
import kotlin.reflect.*

internal class KClassProxy(
    override val state: KClassState, override val interpreter: IrInterpreter
) : ReflectionProxy, KClass<Proxy> {
    override val simpleName: String?
        get() = state.irClass.name.asString() // TODO return null if class has no name
    override val qualifiedName: String?
        get() = state.irClass.internalName()

    override val members: Collection<KCallable<*>>
        get() = state.getMembers(interpreter)
    override val constructors: Collection<KFunction<Proxy>>
        get() = state.getConstructors(interpreter)
    override val nestedClasses: Collection<KClass<*>>
        get() = TODO("Not yet implemented")
    override val objectInstance: Proxy?
        get() = TODO("Not yet implemented")
    override val typeParameters: List<KTypeParameter>
        get() = TODO("Not yet implemented")
    override val supertypes: List<KType>
        get() = TODO("Not yet implemented")
    override val sealedSubclasses: List<KClass<out Proxy>>
        get() = TODO("Not yet implemented")
    override val annotations: List<Annotation>
        get() = TODO("Not yet implemented")

    override val visibility: KVisibility?
        get() = TODO("Not yet implemented")
    override val isFinal: Boolean
        get() = state.irClass.modality == Modality.FINAL
    override val isOpen: Boolean
        get() = state.irClass.modality == Modality.OPEN
    override val isAbstract: Boolean
        get() = state.irClass.modality == Modality.ABSTRACT
    override val isSealed: Boolean
        get() = state.irClass.modality == Modality.SEALED
    override val isData: Boolean
        get() = state.irClass.isData
    override val isInner: Boolean
        get() = state.irClass.isInner
    override val isCompanion: Boolean
        get() = state.irClass.isCompanion
    override val isFun: Boolean
        get() = state.irClass.isFun

    override fun isInstance(value: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }
}
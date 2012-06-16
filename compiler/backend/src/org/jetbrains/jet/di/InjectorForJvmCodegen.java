/*
 * Copyright 2010-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.jetbrains.jet.di;

import org.jetbrains.jet.lang.types.lang.JetStandardLibrary;
import org.jetbrains.jet.lang.resolve.BindingContext;
import java.util.List;
import org.jetbrains.jet.lang.psi.JetFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.jet.lang.resolve.java.CompilerSpecialMode;
import org.jetbrains.jet.codegen.ClassBuilderMode;
import org.jetbrains.jet.codegen.GenerationState;
import org.jetbrains.jet.codegen.ClassBuilderFactory;
import org.jetbrains.jet.codegen.JetTypeMapper;
import org.jetbrains.jet.codegen.ClassCodegen;
import org.jetbrains.jet.codegen.ScriptCodegen;
import org.jetbrains.jet.codegen.intrinsics.IntrinsicMethods;
import org.jetbrains.jet.codegen.ClassFileFactory;
import org.jetbrains.jet.codegen.MemberCodegen;
import org.jetbrains.jet.codegen.ClosureAnnotator;
import org.jetbrains.jet.lang.types.lang.JetStandardLibrary;
import org.jetbrains.jet.lang.resolve.BindingContext;
import java.util.List;
import org.jetbrains.jet.lang.psi.JetFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.jet.lang.resolve.java.CompilerSpecialMode;
import org.jetbrains.jet.codegen.ClassBuilderMode;
import org.jetbrains.jet.codegen.GenerationState;
import org.jetbrains.jet.codegen.ClassBuilderFactory;
import org.jetbrains.annotations.NotNull;
import javax.annotation.PreDestroy;

/* This file is generated by org.jetbrains.jet.di.AllInjectorsGenerator. DO NOT EDIT! */
public class InjectorForJvmCodegen {

    private final JetStandardLibrary jetStandardLibrary;
    private final BindingContext bindingContext;
    private final List<JetFile> listOfJetFile;
    private final Project project;
    private final CompilerSpecialMode compilerSpecialMode;
    private final ClassBuilderMode classBuilderMode;
    private final GenerationState generationState;
    private final ClassBuilderFactory classBuilderFactory;
    private JetTypeMapper jetTypeMapper;
    private ClassCodegen classCodegen;
    private ScriptCodegen scriptCodegen;
    private IntrinsicMethods intrinsics;
    private ClassFileFactory classFileFactory;
    private MemberCodegen memberCodegen;
    private ClosureAnnotator closureAnnotator;

    public InjectorForJvmCodegen(
        @NotNull JetStandardLibrary jetStandardLibrary,
        @NotNull BindingContext bindingContext,
        @NotNull List<JetFile> listOfJetFile,
        @NotNull Project project,
        @NotNull CompilerSpecialMode compilerSpecialMode,
        @NotNull ClassBuilderMode classBuilderMode,
        @NotNull GenerationState generationState,
        @NotNull ClassBuilderFactory classBuilderFactory
    ) {
        this.jetStandardLibrary = jetStandardLibrary;
        this.bindingContext = bindingContext;
        this.listOfJetFile = listOfJetFile;
        this.project = project;
        this.compilerSpecialMode = compilerSpecialMode;
        this.classBuilderMode = classBuilderMode;
        this.generationState = generationState;
        this.classBuilderFactory = classBuilderFactory;
        this.jetTypeMapper = new JetTypeMapper();
        this.classCodegen = new ClassCodegen();
        this.scriptCodegen = new ScriptCodegen();
        this.intrinsics = new IntrinsicMethods();
        this.classFileFactory = new ClassFileFactory();
        this.memberCodegen = new MemberCodegen();
        this.closureAnnotator = new ClosureAnnotator();

        this.jetTypeMapper.setBindingContext(bindingContext);
        this.jetTypeMapper.setClassBuilderMode(classBuilderMode);
        this.jetTypeMapper.setClosureAnnotator(closureAnnotator);
        this.jetTypeMapper.setCompilerSpecialMode(compilerSpecialMode);
        this.jetTypeMapper.setStandardLibrary(jetStandardLibrary);

        this.classCodegen.setJetTypeMapper(jetTypeMapper);
        this.classCodegen.setState(generationState);

        this.scriptCodegen.setBindingContext(bindingContext);
        this.scriptCodegen.setClassFileFactory(classFileFactory);
        this.scriptCodegen.setClosureAnnotator(closureAnnotator);
        this.scriptCodegen.setJetTypeMapper(jetTypeMapper);
        this.scriptCodegen.setMemberCodegen(memberCodegen);
        this.scriptCodegen.setState(generationState);

        this.intrinsics.setMyProject(project);

        this.classFileFactory.setBuilderFactory(classBuilderFactory);
        this.classFileFactory.setState(generationState);

        this.memberCodegen.setState(generationState);

        this.closureAnnotator.setBindingContext(bindingContext);
        this.closureAnnotator.setFiles(listOfJetFile);

        jetTypeMapper.init();

        intrinsics.init();

    }

    @PreDestroy
    public void destroy() {
    }

    public JetStandardLibrary getJetStandardLibrary() {
        return this.jetStandardLibrary;
    }

    public GenerationState getGenerationState() {
        return this.generationState;
    }

    public JetTypeMapper getJetTypeMapper() {
        return this.jetTypeMapper;
    }

    public ClassCodegen getClassCodegen() {
        return this.classCodegen;
    }

    public ScriptCodegen getScriptCodegen() {
        return this.scriptCodegen;
    }

    public IntrinsicMethods getIntrinsics() {
        return this.intrinsics;
    }

    public ClassFileFactory getClassFileFactory() {
        return this.classFileFactory;
    }

    public MemberCodegen getMemberCodegen() {
        return this.memberCodegen;
    }

    public ClosureAnnotator getClosureAnnotator() {
        return this.closureAnnotator;
    }

}

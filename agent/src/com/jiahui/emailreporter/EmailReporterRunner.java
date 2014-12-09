/*
 * Copyright 2000-2014 JetBrains s.r.o.
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

package com.jiahui.emailreporter;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import org.jetbrains.annotations.NotNull;
/**
 * Created by Jiahui.Chen on 12/6/2014.
 */

public class EmailReporterRunner implements AgentBuildRunner, AgentBuildRunnerInfo {
  @NotNull
  public BuildProcess createBuildProcess(@NotNull AgentRunningBuild runningBuild, @NotNull BuildRunnerContext context) throws RunBuildException {
    return new EmailReporterBuildProcess(runningBuild, context);
  }

  @NotNull
  public AgentBuildRunnerInfo getRunnerInfo() {
    return this;
  }

  @NotNull
  public String getType() {
    return "emailreporter";
  }

  public boolean canRun(@NotNull BuildAgentConfiguration agentConfiguration) {
    return true;
  }
}
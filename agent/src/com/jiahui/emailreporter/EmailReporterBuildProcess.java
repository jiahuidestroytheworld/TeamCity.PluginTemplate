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

import jetbrains.buildServer.agent.AgentRunningBuild;
import jetbrains.buildServer.agent.BuildProcess;
import jetbrains.buildServer.agent.BuildProgressLogger;
import jetbrains.buildServer.agent.BuildRunnerContext;
import org.jetbrains.annotations.NotNull;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;


/**
 * Created by Jiahui.Chen on 12/6/2014.
 */



public class EmailReporterBuildProcess implements BuildProcess {
  private final AgentRunningBuild myBuild;
  private final BuildRunnerContext myContext;
  private BuildProgressLogger logger;


  public EmailReporterBuildProcess(@NotNull AgentRunningBuild build, @NotNull BuildRunnerContext context) {
    myBuild = build;
    myContext = context;
  }

  public void start() throws RunBuildException {
    Map<String, String> runnerParams = new HashMap<String, String>(myContext.getRunnerParameters());
    for(Map.Entry<String, String> entry : runnerParams.entrySet()){
      RunnerParamsProvider.setProperty(entry.getKey(), entry.getValue());
    }
    String title = RunnerParamsProvider.props.getProperty("emailTitle");
  }

  public boolean isInterrupted() {
    return false;
  }

  public boolean isFinished() {
    return false;
  }

  public void interrupt() {
  }

  @NotNull
  public BuildFinishedStatus waitFor() throws RunBuildException {
    return BuildFinishedStatus.FINISHED_SUCCESS;
  }
}

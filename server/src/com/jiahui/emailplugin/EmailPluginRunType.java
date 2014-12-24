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

package com.jiahui.emailplugin;

/**
 * Created by Jiahui.Chen on 12/18/2014.
 */

import com.intellij.openapi.util.text.StringUtil;
import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.serverSide.RunType;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import javax.mail.internet.*;
import java.util.*;

import static com.jiahui.emailplugin.EmailBean.*;

public class EmailPluginRunType extends RunType {
  private final PluginDescriptor myDescriptor;



  public EmailPluginRunType(@NotNull final PluginDescriptor descriptor){
    myDescriptor = descriptor;
  }

  @NotNull
  @Override
  public String getType() {
    return RUN_TYPE_NAME;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return DISPLAY_NAME;
  }

  @NotNull
  @Override
  public String getDescription() {
    return DESCRIPTION;
  }

  @Nullable
  @Override
  public PropertiesProcessor getRunnerPropertiesProcessor() {
    return new PropertiesProcessor() {
      @NotNull
      public Collection<InvalidProperty> process(Map<String, String> properties) {
        List<InvalidProperty> invalidResult = new ArrayList<InvalidProperty>();

        if (StringUtil.isEmptyOrSpaces(properties.get("emailFrom"))) {
          invalidResult.add(new InvalidProperty("emailFrom", "receiver's email address cannot be empty"));
        } else {
          try {
            InternetAddress emailAddress = new InternetAddress(properties.get("emailFrom"));
            emailAddress.validate();
          } catch (AddressException e) {
            invalidResult.add(new InvalidProperty("emailFrom", "sender's email address format is not correct"));
          }
        }

        if (StringUtil.isEmptyOrSpaces(properties.get("emailTo"))) {
          invalidResult.add(new InvalidProperty("emailTo", "receiver's email address cannot be empty"));
        } else {
          String validString = verifyEmailAddress(properties.get("emailTo"));
          if(validString.length()>0) {
            invalidResult.add(new InvalidProperty("emailTo", "receiver " + validString));
          }
        }

        if (!StringUtil.isEmptyOrSpaces(properties.get("emailCC"))) {
          String validString = verifyEmailAddress(properties.get("emailCC"));
          if(validString.length()>0) {
            invalidResult.add(new InvalidProperty("emailCC", "CC receiver " + validString));
          }
        }

        if (!StringUtil.isEmptyOrSpaces(properties.get("emailBCC"))) {
          String validString = verifyEmailAddress(properties.get("emailBCC"));
          if(validString.length()>0) {
            invalidResult.add(new InvalidProperty("emailBCC", "BCC receiver " + validString));
          }
        }

        return invalidResult;
      }
    };
  }

  @Nullable
  @Override
  public String getEditRunnerParamsJspFilePath() {
    return myDescriptor.getPluginResourcesPath("emailpluginedit.jsp");
  }

  @Nullable
  @Override
  public String getViewRunnerParamsJspFilePath() {
    return myDescriptor.getPluginResourcesPath("emailpluginview.jsp");
  }

  @Nullable
  @Override
  public Map<String, String> getDefaultRunnerProperties() {
    return Collections.emptyMap();
  }

  public String verifyEmailAddress(String s) {
    boolean invalidEmail = false;
    String[] emailArray = s.split(",");
    StringBuilder sb = new StringBuilder();
    for(String emailadd : emailArray) {
      try {
        emailadd = emailadd.trim();
        InternetAddress emailAddress = new InternetAddress(emailadd);
        emailAddress.validate();
      } catch (AddressException e) {
        invalidEmail = true;
        sb.append(emailadd + ", ");
      }
    }
    if(invalidEmail) {
      sb.append(" do not have correct format");
      return sb.toString();
    }
    else return "";
  }

}
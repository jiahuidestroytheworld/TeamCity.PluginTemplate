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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class RunnerParamsProvider {
  public  static Properties props = new Properties();
  static {
    try {
      FileInputStream fis = new FileInputStream("params.properties");
      props.load(fis);
      fis.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void setProperty(String key, String value){
    try{
      props.setProperty(key, value);
      props.store(new FileOutputStream("params.properties"), "set parameters");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techwandering.common.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Json util class.
 *
 * @author wanshao create time is 2021/4/13
 **/
@Slf4j
public class JacksonUtil {
  
  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  
  /**
   * Convert object to json string.
   *
   * @param obj any object.
   * @return json string
   */
  public static String toJsonString(final Object obj) {
    try {
      return OBJECT_MAPPER.writeValueAsString(obj);
    } catch (Exception e) {
      String msg = "write map to json string error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }
  
  /**
   * Convert object to pretty json string.
   *
   * @param obj any object
   * @return json string
   */
  public static String toJsonPretty(final Object obj) {
    try {
      return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (Exception e) {
      String msg = "write map to json string error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }
  
  /**
   * Parse object from json string.
   *
   * @param message json format message.
   * @param mapClass any class
   * @param <T> generic type
   * @return specified generic class
   */
  public static <T> T readJson(final String message, Class<T> mapClass) {
    try {
      return OBJECT_MAPPER.readerFor(mapClass).readValue(message);
    } catch (Exception e) {
      String msg = "read from json string error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }
  
  /**
   * Get json node from json string.
   *
   * @param message
   * @return
   */
  public static JsonNode getJsonNode(String message) {
    try {
      return OBJECT_MAPPER.readTree(message);
    } catch (Exception e) {
      String msg =
              "read json node from json string error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }
  
}

/*
 * Copyright 2023 TechWandering
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
 * @author wanshao create time is 2021/4/13
 **/
@Slf4j
public class JacksonUtil {

  public static final ObjectMapper objectMapper = new ObjectMapper();

  public static String toJsonString(Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      String msg = "write map to json string error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }

  public static String toJsonPretty(Object obj) {
    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (Exception e) {
      String msg = "write map to json string error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }

  public static <T> T readJson(String message, Class<T> mapClass) {
    try {
      return objectMapper.readerFor(mapClass).readValue(message);
    } catch (Exception e) {
      String msg = "read from json string error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }

  public static JsonNode getJsonNode(String message) {
    try {
      return objectMapper.readTree(message);
    } catch (Exception e) {
      String msg =
          "read json node from json string error.msg:" + ExceptionUtils.getRootCauseMessage(e);
      log.error(msg, e);
      throw new RuntimeException(msg, e);
    }
  }

}

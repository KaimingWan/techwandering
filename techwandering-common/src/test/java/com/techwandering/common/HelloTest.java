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

package com.techwandering.common;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Basic hello test.
 *
 * @author wanshao create time is 2023/2/15
 **/
class HelloTest {
    
    /**
     * It is a main test.
     */
    @Test
    void main() {
        String classpath = System.getProperty("java.class.path");
        System.out.println(classpath);
        Assertions.assertThat("hello").isEqualTo("hello");
    }
    
}

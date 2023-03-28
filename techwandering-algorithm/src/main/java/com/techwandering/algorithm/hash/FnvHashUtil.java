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

package com.techwandering.algorithm.hash;

/**
 * @author wanshao create time is 2023/3/17
 **/
public class FnvHashUtil {
    
    public static final long FNV641A_MAGIC_PRIME = 0x100000001b3L;
    private static final long FNV641A_MAGIC_HASHCODE = 0xcbf29ce484222325L;
    
    public static long fnv1a64(String chars) {
        long hash = FNV641A_MAGIC_HASHCODE;
        for (int i = 0; i < chars.length(); ++i) {
            char c = chars.charAt(i);
            hash ^= c;
            hash *= FNV641A_MAGIC_PRIME;
        }
        return hash;
    }
    
    public static long fnv1a64IgnoreCase(String chars) {
        long hash = FNV641A_MAGIC_HASHCODE;
        for (int i = 0; i < chars.length(); ++i) {
            char c = chars.charAt(i);
            // convert to lowercase
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + 32);
            }
            
            hash ^= c;
            hash *= FNV641A_MAGIC_PRIME;
        }
        return hash;
    }
    
}

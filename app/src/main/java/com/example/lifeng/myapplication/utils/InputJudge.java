/*
 * ------------------------------------------------------------------------------------
 * CMB Confidential
 *
 * Copyright (C) 2013 China Merchants Bank Co., Ltd. All rights reserved.
 *
 * No part of this file may be reproduced or transmitted in any form or by any means,
 * electronic, mechanical, photocopying, recording, or otherwise, without prior
 * written permission of China Merchants Bank Co., Ltd.
 * ------------------------------------------------------------------------------------
 */

package com.example.lifeng.myapplication.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lifeng
 * @version 1.0 16/7/22
 * @description 输入内容判断工具类
 */
public class InputJudge {
    // TODO: 16/7/22 判断是否为正小数有待完善
    public static boolean isPositiveDoubleNumber(String str) {
        Pattern pattern = Pattern.compile("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*");
        Matcher isPositiveDoubleNumber = pattern.matcher(str);
        if (!isPositiveDoubleNumber.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isPositiveInteger(String str) {
        Pattern pattern = Pattern.compile("^\\+{0,1}[1-9]\\d*");
        Matcher isPositiveInteger = pattern.matcher(str);
        if (!isPositiveInteger.matches()) {
            return false;
        }
        return true;
    }
}

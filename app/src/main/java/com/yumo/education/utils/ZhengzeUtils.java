package com.yumo.education.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ZhengzeUtils {
    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(15[0-3, 5-9])|(18[0-9])|(17[0-8])|(199)|(147))\\d{8}$";//未来也会有92/98/16/19号段
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /***
     * 判断是否密码
     * 解析：^ 匹配字符串的开始位置
     (?![0-9]+$)断言此位置之后，字符串结尾之前，所有的字符不能全部由数字组成
     (?![a-zA-Z]+$)断言此位置之后，字符串结尾之前，所有的字符不能全部由26个英文字母组成
     [0-9A-Za-z]{6,10}匹配整个字符串由6~10位由数字和26个英文字母混合而成
     $匹配字符串的结束位置
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isMima(String str) throws PatternSyntaxException {
        // 匹配6~20位由数字和26个英文字母混合而成的密码：
        String regExp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isEmail(String str) throws PatternSyntaxException {
        // Email的验证
        String regExp = "^[a-zA-Z0-9_\\-]{1,}@[a-zA-Z0-9_\\-]{1,}\\.[a-zA-Z0-9_\\-.]{1,}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /***
     * 下面是一些常用的实例
     1.匹配 “a”,"*","1" 三个字符中的任何一个
     [a,\*,1]
     2.匹配负整数
     ^-[1-9]\d*
     3.验证密码，以字母开头，长度在6~18之间，只能包含字符、数字和下划线
     ^[a-zA-Z]\w{5,17}$
     4.Email的验证
     要求：(1)必须包含一个"@"
     　　 (2)在"@"后面的字符串至少包含一个"."号
     　　 (3)"@"前面和后面的字符串由一个或一个以上字母、数字、下划线或减号构成
     ^[a-zA-Z0-9_\-]{1,}@[a-zA-Z0-9_\-]{1,}\.[a-zA-Z0-9_\-.]{1,}$
     5.验证InternetURL地址
     ^http://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$
     6.最多允许使用 40 个大写字母和小写字母
     [a-zA-Z]{1,40}
     7.只能输入零和非零开头的数字
     ^(0|[1-9][0-9]*)$
     8.只能输入有两位小数的正实数
     ^[0-9]+(.[0-9]{2})?$
     9.只能输入非零的正整数
     ^\+?[1-9][0-9]*$
     10.只能输入由数字、26个英文字母或者下划线组成的字符串
     ^\w+$
     ^[\d|A-z|_]+$
     11.匹配例如：yyyy-mm-dd或yyyy/mm/dd的日期格式
     \d{4}([-/])\d{2}\1\d{2}
     12. 匹配其中的数字字符串
     "[\"3812662409\",\"3812633637\",\"3812627686\",\"3812651467\",\"3812628047\",\"3812650203\"]"
     例如：3812662409,3812633637...
     \d+
     ***/

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
    // 判断手机号码是否规则
    public static boolean isPhoneNumber(String input) {
        String regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}";
        Pattern p = Pattern.compile(regex);
        return p.matches(regex, input);
    }

    //判断是否是数字
    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }
//这里是判断YYYY-MM-DD或YYYY-MM-DD hh:mm:ss  这种格式的，基本上把闰年和2月等的情况都考虑进去了，不过我已经忘了在哪里找到的。
    public static boolean isDate(String str) throws PatternSyntaxException {
        // 匹配：
        String regExp = "(^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$)|(^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$)";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
//    一、简单的日期判断（YYYY/MM/DD）：
//
//            　　^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$
//
//    二、演化的日期判断（YYYY/MM/DD| YY/MM/DD）：
//
//            　　^(^(\d{4}|\d{2})(\-|\/|\.)\d{1,2}\3\d{1,2}$)|(^\d{4}年\d{1,2}月\d{1,2}日$)$
//
//    三、加入闰年的判断的：
//
//            　　^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$
//
////日期格式yyyy
//    PatternsDict.date_y= /^(\d{4})$/;
//
////日期格式yyyy-mm
//    PatternsDict.date_ym= /^(\d{4})-(0\d{1}|1[0-2])$/;
//
////日期格式yyyy-mm-dd
//    PatternsDict.date_ymd= /^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
//
////时间格式hh
//    PatternsDict.time_h=/^(0\d{1}|1\d{1}|2[0-3])$/;
//
////时间格式hh:mm
//    PatternsDict.time_hm=/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/;
//
////时间格式hh:mm:ss
//    PatternsDict.time_hms=/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/;
//时间格式hh:mm:ss
//    PatternsDict.time_hms=/^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1}):([0-5]\d{1})$/;


//   【aspx页面内：<%# DataBinder.Eval(Container.DataItem,"Company_Ureg_Date")%>
//    显示为： 2004-8-11 19:44:28
//    我只想要：2004-8-11 】
//
//   <%# DataBinder.Eval(Container.DataItem,"Company_Ureg_Date","{0:yyyy-M-d}")%>
//
//    应该如何改？
//
//
//            【格式化日期】
//    取出来,一般是object
//            ((DateTime)objectFromDB).ToString("yyyy-MM-dd");
//
//
//    A.以下正确的输入格式： [2004-2-29], [2004-02-29 10:29:39 pm], [2004/12/31]
//
//            ^((\d{2}(([02468][048])|([13579][26]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])|
//            (3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|([1-2][0-9])))))
//            |(\d{2}(([02468][1235679])|([13579][01345789]))[\-\/\s]?((((0?[13578])|(1[02]))[\-\/\s]?((0?[1-9])|([1-2][0-9])
//            |(3[01])))|(((0?[469])|(11))[\-\/\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-\/\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\s(((0?[1-9])|(1[0-2]))\:([0-5][0-9])((\s)|(\:([0-5][0-9])\s))([AM|PM|am|pm]{2,2})))?$
//
//    B.以下正确的输入格式：[0001-12-31], [9999 09 30], [2002/03/03]
//
//            ^\d{4}[\-\/\s]?((((0[13578])|(1[02]))[\-\/\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\-\/\s]?(([0-2][0-9])|(30)))|(02[\-\/\s]?[0-2][0-9]))$

    public static boolean isSMSTime(String str) throws PatternSyntaxException {
        // 匹配：YYYY-MM-DD hh:mm:ss 06:00:00 12:00:00 20:00:00   01:00:00
        String regExp = "[0-9]{4}-[0-9]{2}-[0-9]{2} 06|12|20|01{2}:00{2}:00{2}";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
   // date=date.trim();
//    String a1 = "[0-9]{4}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}";//yyyyMMddHHmmss
//    String a2 = "[0-9]{4}[0-9]{2}[0-9]{2}";//yyyyMMdd
//    String a3 = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";//yyyy-MM-dd HH:mm:ss
//    String a4 = "[0-9]{4}-[0-9]{2}-[0-9]{2}";//yyyy-MM-dd
//    String a5= "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";//yyyy-MM-dd  HH:mm

}

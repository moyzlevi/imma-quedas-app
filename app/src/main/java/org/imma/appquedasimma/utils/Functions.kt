package org.imma.appquedasimma.utils

import java.util.*

class Functions {
     fun generateRandomID():String{
         val random = Random()

         val array = arrayOf(
             (0 until 10).random(),
             (0 until 10).random(),
             (0 until 10).random(),
             (0 until 10).random(),
             (0 until 10).random(),
             (0 until 10).random())
         return array.joinToString("")
     }
}
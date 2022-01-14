package com.freddy.shine.kotlin.cipher

/**
 * 抽象的加解密器
 *
 * 如果需要自定义加解密器，继承此[AbstractCipher]，实现[encrypt]及[decrypt]方法即可
 * @see [DefaultCipher]
 * @author: FreddyChen
 * @date  : 2022/01/13 16:10
 * @email : freddychencsc@gmail.com
 */
abstract class AbstractCipher : ICipher
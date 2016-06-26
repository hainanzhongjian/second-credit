package com.second.credit.core.model;

/**
 * @note 数学计算单位-分数工具类
 * @author wangmeng
 * @date 2015年8月9日下午3:10:59
 */
public class Fractions {

    private int numerator; // 分数-分子
    private int numeratorMul; // 分数-分子倍数
    private int denominator; // 分数-分母
    private int denominatorMul; // 分数-分母倍数

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public int getNumeratorMul() {
        return numeratorMul;
    }

    public void setNumeratorMul(int numeratorMul) {
        this.numeratorMul = numeratorMul;
    }

    public int getDenominatorMul() {
        return denominatorMul;
    }

    public void setDenominatorMul(int denominatorMul) {
        this.denominatorMul = denominatorMul;
    }

    @Override
    public String toString() {
        return "FractionsUtils [numerator=" + numerator + ", numeratorMul=" + numeratorMul + ", denominator="
                + denominator + ", denominatorMul=" + denominatorMul + "]";
    }
}

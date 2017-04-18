package sample;

/**
 * @author Luis Casillas
 * Octubre 2016
 */

public class BiblioFuzzy{
    /*
    Funciones de Membresía
    */
    public static double trapecioAbiertoDer(double u,double a,double b){
        if (u>b) return 1.0;
        if (u<a) return 0.0;
        if (a<=u&&u<=b) return (u-a)/(b-a);
        return -1;
    }
    public static double trapecioAbiertoIzq(double u,double a,double b){
        if (u>b) return 0.0;
        if (u<a) return 1.0;
        if (a<=u&&u<=b) return (b-u)/(b-a);
        return -1;
    }
    public static double triangular(double u,double a,double b,double c){
        if (u<a||u>c) return 0.0;
        if (a<=u&&u<b) return (u-a)/(b-a);
        if (b<=u&&u<=c) return (c-u)/(c-b);
        return -1;
    }
    public static double trapezoidal(double u,double a,double b,double c,double d){
        if (u<a||u>d) return 0.0;
        if (b<=u&&u<=c) return 1.0;
        if (a<=u&&u<b) return (u-a)/(b-a);
        if (c<u&&u<=d) return (d-u)/(d-c);
        return -1;
    }
    public static double curva_S(double u,double a,double b){
        if (u>b) return 1.0;
        if (u<a) return 0.0;
        if (a<=u&&u<=b) return 0.5*(1+Math.cos(((u-b)/(b-a))*Math.PI));
        return -1;
    }
    public static double curva_Z(double u,double a,double b){
        if (u>b) return 0.0;
        if (u<a) return 1.0;
        if (a<=u&&u<=b) return 0.5*(1+Math.cos(((u-a)/(b-a))*Math.PI));
        return -1;
    }
    public static double triangularSuave(double u,double a,double b,double c){
        if (u<a||u>c) return 0.0;
        if (a<=u&&u<b) return 0.5*(1+Math.cos(((u-b)/(b-a))*Math.PI));
        if (b<=u&&u<=c) return 0.5*(1+Math.cos(((b-u)/(c-b))*Math.PI));
        return -1;
    }
    public static double trapezoidalSuave(double u,double a,double b,double c,double d){
        if (u<a||u>d) return 0.0;
        if (b<=u&&u<=c) return 1.0;
        if (a<=u&&u<b) return 0.5*(1+Math.cos(((u-b)/(b-a))*Math.PI));
        if (c<u&&u<=d) return 0.5*(1+Math.cos(((c-u)/(d-c))*Math.PI));
        return -1;
    }
    /*
    Accesorios de apoyo...
    */
    private static double min(double a,double b){
        return a<b?a:b;
    }
    private static double max(double a,double b){
        return a>b?a:b;
    }
    /*
    Operadores Lógicos Fuzzy
    */
    public static double compAND(double ma_u,double mb_u){
        return min(ma_u,mb_u);
    }
    public static double compOR(double ma_u,double mb_u){
        return max(ma_u,mb_u);
    }
    public static double niega(double ma_u){
        return 1.0-ma_u;
    }
    /*
    Implicación Fuzzy
    */
    public static double implicaZadeh(double ma_x,double mb_y){
        return max(min(ma_x,mb_y),niega(ma_x));
    }
    public static double implicaMamdani(double ma_x,double mb_y){
        return min(ma_x,mb_y);
    }
    public static double implicaGodel(double ma_x,double mb_y){
        return (ma_x<=mb_y)?1:mb_y;
    }
}
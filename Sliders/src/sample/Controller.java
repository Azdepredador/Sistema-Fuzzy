package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller extends  Main  {

    public static String [] conjuntosFuerza={"Poca Fuerza","Fuerza Normal","Mucha Fuerza"};
    public static String [] conjuntosResistencia={"Poca Armadura","Armadura Adecuada","Armadura Pesada"};
    public static String [] conjuntosDestreza={"Destreza Menor","Destreza Normal","Destreza Alta"};
    public static String [] conjuntosClase={"Cazador",
            "Mago",
            "Guerrero",
            "Hechicero",
            "Paladin"};

    public static double [] nivsMemFuerza= new double[3];
    public static double [] nivsMemDestreza= new double[3];
    public static double [] nivsMemResistencia= new double[3];
    public static double [] nivsMemClase= new double[5];


    public int posNivMemMay(double [] nivsMem){
        int posMay=0;
        for(int i=0;i<nivsMem.length;i++){
            if (nivsMem[i]>nivsMem[posMay]) posMay=i;
        }
        return posMay;
    }

    public String fuzzificarFuerza(double datoNitidoFuerza){
            String conjunto="";
            prodMembsFuerza(datoNitidoFuerza);
            conjunto=conjuntosFuerza[posNivMemMay(nivsMemFuerza)];
            return conjunto;

    }

    public String fuzzificarResistencia(double datoNitidoResistencia){
        String conjunto="";
        prodMembsResistencia(datoNitidoResistencia);
        conjunto=conjuntosResistencia[posNivMemMay(nivsMemResistencia)];
        return conjunto;

    }

    public String fuzzificarDestreza(double datoNitidoDestreza){
        String conjunto="";
        prodMembsDestreza(datoNitidoDestreza);
        conjunto=conjuntosDestreza[posNivMemMay(nivsMemDestreza)];
        return conjunto;

    }

    public void prodMembsFuerza(double datoNitidoFuerza){
        nivsMemFuerza[0]=BiblioFuzzy.curva_Z(datoNitidoFuerza,20,50);
        nivsMemFuerza[1]=BiblioFuzzy.triangularSuave(datoNitidoFuerza,20,50,100);
        nivsMemFuerza[2]=BiblioFuzzy.curva_S(datoNitidoFuerza,50,100);
        muestraNivMemMay(("Membresías Fuerza= "+datoNitidoFuerza),nivsMemFuerza);


    }

    public void prodMembsDestreza(double datoNitidoDestreza){
        nivsMemDestreza[0]=BiblioFuzzy.curva_Z(datoNitidoDestreza,20,50);
        nivsMemDestreza[1]=BiblioFuzzy.triangularSuave(datoNitidoDestreza,20,50,100);
        nivsMemDestreza[2]=BiblioFuzzy.curva_S(datoNitidoDestreza,50,100);
        muestraNivMemMay(("Membresías Destreza= "+datoNitidoDestreza),nivsMemDestreza);


    }

    public void prodMembsResistencia(double datoNitidoResistencia){
        nivsMemResistencia[0]=BiblioFuzzy.curva_Z(datoNitidoResistencia,20,50);
        nivsMemResistencia[1]=BiblioFuzzy.triangularSuave(datoNitidoResistencia,20,50,100);
        nivsMemResistencia[2]=BiblioFuzzy.curva_S(datoNitidoResistencia,50,100);
        muestraNivMemMay(("Membresías Resistencia= "+datoNitidoResistencia),nivsMemResistencia);


    }


    public void muestraNivMemMay(String msg, double [] nivsMem){
        System.out.print(msg+": [");
        for(int i=0;i<nivsMem.length;i++){
            System.out.print(nivsMem[i]+(i+1==nivsMem.length?"]":", "));
        }
        System.out.println();
    }

    public String inferirClaseDifusaCualitativa(String fuerzaDifusa,
                                                String resistenciaDifusa,
                                                String destrezaDifusa){
        String claseDifuso="";
        // Regla de inferencia difusa #1
        if ((resistenciaDifusa.equals("Poca Armadura") | destrezaDifusa.equals("Destreza Menor")) &
                fuerzaDifusa.equals("Poca Fuerza")) claseDifuso="Cazador";
            // Regla de inferencia difusa #2
        else
        if ((resistenciaDifusa.equals("Poca Armadura") | destrezaDifusa.equals("Destreza Menor")) &
                fuerzaDifusa.equals("Fuerza Normal")) claseDifuso="Mago";
            // Regla de inferencia difusa #3
        else
        if ((resistenciaDifusa.equals("Poca Armadura") | destrezaDifusa.equals("Destreza Menor")) &
                fuerzaDifusa.equals("Mucha Fuerza")) claseDifuso="Guerrero";
            // Regla de inferencia difusa #4
        else
        if ((resistenciaDifusa.equals("Armadura Adecuada") | destrezaDifusa.equals("Destreza Normal")) &
                fuerzaDifusa.equals("Poca Fuerza")) claseDifuso="Mago";
            // Regla de inferencia difusa #5
        else
        if ((resistenciaDifusa.equals("Armadura Adecuada") | destrezaDifusa.equals("Destreza Normal")) &
                fuerzaDifusa.equals("Fuerza Normal")) claseDifuso="Guerrero";
            // Regla de inferencia difusa #6
        else
        if ((resistenciaDifusa.equals("Armadura Adecuada") | destrezaDifusa.equals("Destreza Normal")) &
                fuerzaDifusa.equals("Mucha Fuerza")) claseDifuso="Hechicero";
            // Regla de inferencia difusa #7
        else
        if ((resistenciaDifusa.equals("Armadura Pesada") | destrezaDifusa.equals("Destreza Alta")) &
                fuerzaDifusa.equals("Poca Fuerza")) claseDifuso="Guerrero";
            // Regla de inferencia difusa #8
        else
        if ((resistenciaDifusa.equals("Armadura Pesada") | destrezaDifusa.equals("Destreza Alta")) &
                fuerzaDifusa.equals("Fuerza Normal")) claseDifuso="Hechicero";
            // Regla de inferencia difusa #9
        else
        if ((resistenciaDifusa.equals("Armadura Pesada") | destrezaDifusa.equals("Destreza Alta")) &
                fuerzaDifusa.equals("Mucha Fuerza")) claseDifuso="Paladin";
        return claseDifuso;
    }

    private static double min(double a,double b){ return a<b?a:b; }
    private static double max(double a,double b){ return a>b?a:b; }

    public double inferirClaseDifusaCuantitativo(String fuerzaDifusa,
                                                 String resistenciaDifusa,
                                                 String destrezaDifusa){

        double nivMemFuerza,nivMemResistencia,nivMemDestreza,nivMemClase;

        nivMemFuerza= nivsMemFuerza[posNivMemMay(nivsMemFuerza)];
        nivMemResistencia= nivsMemResistencia[posNivMemMay(nivsMemResistencia)];
        nivMemDestreza= nivsMemDestreza[posNivMemMay(nivsMemDestreza)];

        nivMemClase=min(max(nivMemResistencia,nivMemDestreza),nivMemFuerza);

        if ((resistenciaDifusa.equals("Poca Armadura") | destrezaDifusa.equals("Destreza Menor")) &
                fuerzaDifusa.equals("Poca Fuerza")) nivsMemClase[0]=nivMemClase;
            // Regla de inferencia difusa #2
        else
        if ((resistenciaDifusa.equals("Poca Armadura") | destrezaDifusa.equals("Destreza Menor")) &
                fuerzaDifusa.equals("Fuerza Normal")) nivsMemClase[1]=nivMemClase;
            // Regla de inferencia difusa #3
        else
        if ((resistenciaDifusa.equals("Poca Armadura") | destrezaDifusa.equals("Destreza Menor")) &
                fuerzaDifusa.equals("Mucha Fuerza")) nivsMemClase[2]=nivMemClase;
            // Regla de inferencia difusa #4
        else
        if ((resistenciaDifusa.equals("Armadura Adecuada") | destrezaDifusa.equals("Destreza Normal")) &
                fuerzaDifusa.equals("Poca Fuerza")) nivsMemClase[1]=nivMemClase;
            // Regla de inferencia difusa #5
        else
        if ((resistenciaDifusa.equals("Armadura Adecuada") | destrezaDifusa.equals("Destreza Normal")) &
                fuerzaDifusa.equals("Fuerza Normal")) nivsMemClase[2]=nivMemClase;
            // Regla de inferencia difusa #6
        else
        if ((resistenciaDifusa.equals("Armadura Adecuada") | destrezaDifusa.equals("Destreza Normal")) &
                fuerzaDifusa.equals("Mucha Fuerza")) nivsMemClase[3]=nivMemClase;
            // Regla de inferencia difusa #7
        else
        if ((resistenciaDifusa.equals("Armadura Pesada") | destrezaDifusa.equals("Destreza Alta")) &
                fuerzaDifusa.equals("Poca Fuerza")) nivsMemClase[2]=nivMemClase;
            // Regla de inferencia difusa #8
        else
        if ((resistenciaDifusa.equals("Armadura Pesada") | destrezaDifusa.equals("Destreza Alta")) &
                fuerzaDifusa.equals("Fuerza Normal")) nivsMemClase[3]=nivMemClase;
            // Regla de inferencia difusa #9
        else
        if ((resistenciaDifusa.equals("Armadura Pesada") | destrezaDifusa.equals("Destreza Alta")) &
                fuerzaDifusa.equals("Mucha Fuerza")) nivsMemClase[4]=nivMemClase;
        return nivsMemClase[posNivMemMay(nivsMemClase)];


    }

    public double desfuzzificar(String claseDifuso,double nivMemClase){
        switch(claseDifuso){
            case "Cazador":return nivMemClase*20;
            case "Mago":return nivMemClase*35;
            case "Guerrero":return nivMemClase*50;
            case "Hechicero":return nivMemClase*75;
            case "Paladin":return nivMemClase*100;
        }
        return 0.0;
    }

    public void ejecutarFuzzy(){

        double fuerzaNitida,destrezaNitida,resistenciaNitida,nivMemClase,claseNitido;
        String fuerzaDifusa,destrezaDifusa,resistenciaDifusa, claseDifuso;


        fuerzaNitida= fuerzaSlider.getValue();
        destrezaNitida= destrezaSlider.getValue();
        resistenciaNitida= resistenciaSlider.getValue();

        fuerzaDifusa=fuzzificarFuerza(fuerzaNitida);
        resistenciaDifusa=fuzzificarResistencia(resistenciaNitida);
        destrezaDifusa=fuzzificarDestreza(destrezaNitida);

        System.out.println("Para Fuerza="+fuerzaNitida+" corresponde: "+fuerzaDifusa);
        System.out.println("Para Resistencia="+resistenciaNitida+" corresponde: "+resistenciaDifusa);
        System.out.println("Para Destreza="+destrezaNitida+" corresponde: "+destrezaDifusa);

        claseDifuso=inferirClaseDifusaCualitativa(fuerzaDifusa,resistenciaDifusa,destrezaDifusa);

        respuestaL.setText("Su clase recomendada es: "+claseDifuso);

        nivMemClase=inferirClaseDifusaCuantitativo(fuerzaDifusa,resistenciaDifusa,destrezaDifusa);

        System.out.println("Con una certeza de: "+nivMemClase);
        muestraNivMemMay("Membresías Clase", nivsMemClase);

        claseNitido=desfuzzificar(claseDifuso,nivMemClase);

        System.out.println("Desfuzzificación correspondiente: "+claseNitido);


    }



}

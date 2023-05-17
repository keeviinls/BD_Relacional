package EjericicioSimulacroExamen;

public class EmisoraTradicional extends Emisora{
    Frecuencia frecuencia;

    double num_frecuencia;

    public EmisoraTradicional(int num_emisora, String nombre_emisora, int num_oyentes, String frecuencia, double num_frecuencia) throws AccionInvalida {
        super(num_emisora, nombre_emisora, num_oyentes);
        if (Frecuencia.AM.toString().equals(frecuencia) || Frecuencia.FM.toString().equals(frecuencia)){
            this.frecuencia = Frecuencia.valueOf(frecuencia);
        }
        else {
            throw new AccionInvalida("no ha introducido una frecuencia correcta.");
        }

        this.num_frecuencia = num_frecuencia;
    }

    public Frecuencia getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) throws AccionInvalida {
        if (Frecuencia.AM.toString().equals(frecuencia) || Frecuencia.FM.toString().equals(frecuencia)){
            this.frecuencia = Frecuencia.valueOf(frecuencia);
        }
        else {
            throw new AccionInvalida("no ha introducido una frecuencia correcta.");
        }

    }

    public double getNum_frecuencia() {
        return num_frecuencia;
    }

    public void setNum_frecuencia(double num_frecuencia) {
        this.num_frecuencia = num_frecuencia;
    }


    @Override
    double ganancias() {
        return num_oyentes*0.023;
    }

    @Override
    public String toString() {
        return "EmisoraTradicional{" +
                "frecuencia=" + frecuencia +
                ", num_frecuencia=" + num_frecuencia +
                ", num_emisora=" + num_emisora +
                ", nombre_emisora='" + nombre_emisora + '\'' +
                ", emitiendo=" + emitiendo +
                ", beneficios=" + beneficios +
                ", num_oyentes=" + num_oyentes +
                "} " + super.toString();
    }
}

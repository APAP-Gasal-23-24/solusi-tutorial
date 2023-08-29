package apap.tutorial.celsiusConverter.model;

public class CelsiusConverter {
    private Double celsius;

    public CelsiusConverter(Double celsius){
        this.celsius = celsius;
    }

    public String ConvertToFahrenheit(){
        double fahrenheit = (celsius * 9 /5) + 32 ;
        return String.format("%.2f", fahrenheit);
    }
    public String ConvertToKelvin(){
        double kelvin = celsius + 273.15;
        return String.format("%.2f", kelvin);
    }
    public String ConvertToRankine(){
        double rankine = (celsius + 273.15) * 9/5;
        return String.format("%.2f", rankine);
    }

    public double getCelsius(){return celsius;}
}

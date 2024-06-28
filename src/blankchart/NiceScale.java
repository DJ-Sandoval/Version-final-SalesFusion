
package blankchart;

/**
 *
 * @author Jose
 */
public class NiceScale {
   private double min;
   private double max;
   private int maxTicks = 10;
   private double tickSpacing;
   private double range;
   private double niceMin;
   private double niceMax;

    public NiceScale(final double MIN, final double MAX) {
        this.min = MIN;
        this.max = MAX;
        calculated();
    }
    
    public void setMin(double min) {
        this.min = min;
        calculated();
    }
    
    public double getMin() {
        return min;
    }

    public void setMinMax(final double MIN, final double MAX) {
        this.min = MIN;
        max = MAX;
        calculated();
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
        calculated();
    }

    public int getMaxTicks() {
        return maxTicks;
    }

    public void setMaxTicks(final int MAX_TICKS) {
        maxTicks = MAX_TICKS;
        calculated();
    }

    public double getTickSpacing() {
        return tickSpacing;
    }

    public void setTickSpacing(double tickSpacing) {
        this.tickSpacing = tickSpacing;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getNiceMin() {
        return niceMin;
    }

    public void setNiceMin(double niceMin) {
        this.niceMin = niceMin;
    }

    public double getNiceMax() {
        return niceMax;
    }

    public void setNiceMax(double niceMax) {
        this.niceMax = niceMax;
    }
    
    
    
    // Metodos alternos
    private void calculated() {
        range = niceNum(max - min, false);
        tickSpacing = niceNum(range / (maxTicks - 1), true);
        niceMin = Math.floor(min / tickSpacing) * tickSpacing;
        niceMax = Math.ceil(max / tickSpacing) * tickSpacing;
    }
    
    private double niceNum(final double RANGE, final boolean ROUND) {
        double exponent; // Exponente de RANGO
        double fraction; // Parte fraccional de RANGO
        double niceFraction; // bonita fraccion redondeada
        
        exponent = Math.floor(Math.log10(RANGE));
        fraction = RANGE / Math.pow(10, exponent);
        
        if (ROUND) {
            if (fraction < 1.5) {
                niceFraction = 1;
            } else if (fraction < 3) {
                niceFraction = 2;
            } else if (fraction < 7) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        } else {
            if (fraction <= 1) {
                niceFraction = 1;
            } else if (fraction <= 2) {
                niceFraction = 2;
            } else if (fraction <= 5) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        }
        return niceFraction * Math.pow(10, exponent);
    }
   
}

public class Fraction {

        private long num;
        private long den;

        public long GCD(){
            long gcd = Math.abs(this.num);
            long remainder = Math.abs(this.den);
            while(remainder !=0){
                long temp = remainder;
                remainder = gcd % remainder;
                gcd = temp;
            }
            return gcd;
        }

        public void reduce(){
            long divisor = GCD();
            num = num / divisor;
            den = den / divisor;
        }

        public Fraction(){
            num = 0;
            den = 1;
        }

        public Fraction(long n, long d) throws ArithmeticException{
            num = n;
            den = d;

            if(den == 0){
                throw new ArithmeticException("Denominator cannot be 0");
            }

            if(den < 0) {
                num *= -1;
                den *= -1;
            }
            reduce();

        }

        public Fraction plus(Fraction rhs){
            long denominator = den * rhs.den;
            long numerator = (num * rhs.den) + (rhs.num * den);
            return new Fraction(numerator, denominator);

        }

        public Fraction minus(Fraction rhs){
            long denominator = den * rhs.den;
            long numerator = (num * rhs.den) - (rhs.num * den);
            return new Fraction(numerator, denominator);
        }

        public Fraction times(Fraction rhs){
            long denominator = den * rhs.den;
            long numerator = num * rhs.num;
            return new Fraction(numerator, denominator);
        }

        public Fraction dividedBy(Fraction rhs){
            long denominator = den * rhs.num;
            long numerator = num * rhs.den;
            return new Fraction(numerator, denominator);
        }

        public Fraction reciprocal(){
            return new Fraction(den, num);
        }

        public double toDouble(){
            return num / (double)den;
        }

        public String toString(){
            String numerator = "" + num;
            String denominator = "" + den;
            return numerator + "/" + denominator;
        }

}

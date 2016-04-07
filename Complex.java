//-----------------------------------------------------------------------------
//   Complex.java
//   Represents complex numbers as a pair of doubles
//-----------------------------------------------------------------------------

class Complex{

   //--------------------------------------------------------------------------
   // Private Data Fields 
   //--------------------------------------------------------------------------
   private double re;
   private double im;
   
   //--------------------------------------------------------------------------
   // Public Constant Fields 
   //--------------------------------------------------------------------------
   public static final Complex ONE = Complex.valueOf(1,0);
   public static final Complex ZERO = Complex.valueOf(0,0);
   public static final Complex I = Complex.valueOf(0,1);

   //--------------------------------------------------------------------------
   // Constructors 
   //--------------------------------------------------------------------------
   Complex(double a, double b){
      this.re = a;
      this.im = b;
   }

   Complex(double a){
      this.re = a;
      this.im = 0;
   }

   Complex(String s){
      // Fill in this constructor.
      // It should accept expressions like "-2+3i", "2-3i", "3", "5i", etc..
      // Throw a NumberFormatException if s cannot be parsed as Complex.
      double[] reim = Complex.parseComplex(s);
      this.re = reim[0];
      this.im = reim[1];
   }
   
   

   //---------------------------------------------------------------------------
   // Public methods 
   //---------------------------------------------------------------------------

   // Complex arithmetic -------------------------------------------------------

   // copy()
   // Return a new Complex equal to this Complex
   Complex copy(){
      Complex a = new Complex(this.re, this.im);
      return a;
   }
   
   // add()
   // Return a new Complex representing the sum this plus z.
   Complex add(Complex z){
      Complex y = this;
      double real = y.re + z.re;
      double imag = y.im + z.im;
      return new Complex(real, imag);
   }
   
   // negate()
   // Return a new Complex representing the negative of this.
   Complex negate(){
      Complex a = this;
      a.re *= -1;
      a.im *= -1;
      return new Complex(this.re, this.im);
   }

   // sub()
   // Return a new Complex representing the difference this minus z.
   Complex sub(Complex z){
      Complex y = this;
      double real = y.re - z.re;
      double imag = y.im - z.im;
      return new Complex(real, imag);
   }

   // mult()
   // Return a new Complex representing the product this times z.
   Complex mult(Complex z){
      double real, imag;
      real = (this.re * z.re) - (this.im * z.im);
      imag = (this.re * z.im) + (this.im * z.re);
      return new Complex(real, imag);
   }

   // recip()
   // Return a new Complex representing the reciprocal of this.
   // Throw an ArithmeticException with appropriate message if 
   // this.equals(Complex.ZERO).
   Complex recip(){
      Complex y = this;
      if(this.equals(Complex.ZERO)){
         throw new ArithmeticException("Cannot devideby zero");
      } else {
         double real = y.re / (Math.pow(y.re, 2)+Math.pow(y.im, 2)); 
         double imag = -y.im / (Math.pow(y.re, 2)+Math.pow(y.im, 2));
         return new Complex(real, imag);
      }
   }

   // div()
   // Return a new Complex representing the quotient of this by z.
   // Throw an ArithmeticException with appropriate message if 
   // z.equals(Complex.ZERO).
   Complex div(Complex z){
      Complex y = this;
      double real, imag;
      if(z.equals(Complex.ZERO)){
         throw new ArithmeticException("Cannot devide by zero");
      } else {
         real = ((y.re * z.re)+(y.im * z.im))
                       / (Math.pow(z.re, 2)+Math.pow(z.im, 2)); 
         imag = (-(y.re * z.im)+(y.im * z.re))
                       / (Math.pow(z.re, 2)+Math.pow(z.im, 2));
         return new Complex(real, imag);
      }
   }

   // conj()
   // Return a new Complex representing the conjugate of this Complex.
   Complex conj(){
      this.im *= -1;
      return new Complex(this.re, this.im);
   }
   
   // Re()
   // Return the real part of this.
   double Re(){
      return this.re;
   }

   // Im()
   // Return the imaginary part of this.
   double Im(){
      return this.im;
   }

   // abs()
   // Return the modulus of this Complex, i.e. the distance between 
   // points (0, 0) and (re, im).
   double abs(){
      return Math.sqrt(Math.pow(this.re,2)+Math.pow(this.im,2));  
   }

   // arg()
   // Return the argument of this Complex, i.e. the angle this Complex
   // makes with positive real axis.
   double arg(){
      return Math.atan2(im, re);
   }

   // Other functions ---------------------------------------------------------
   
   // toString()
   // Return a String representation of this Complex.
   // The String returned will be readable by the constructor Complex(String s)
   public String toString(){
      String s = "";
      if(this.re != 0) s += String.valueOf(re);
      if(this.im > 0 && this.re != 0) s += "+";
      if(this.im != 0) s += String.valueOf(im)+"i";
      return s;
   }

   // equals()
   // Return true iff this and obj have the same real and imaginary parts.
   public boolean equals(Object obj){
      boolean equals = false;
      Complex c;
      if(obj instanceof Complex){
         c = (Complex) obj;
         equals = (this.re == c.re && this.im == c.im);
      }
      return equals;
   }

   // valueOf()
   // Return a new Complex with real part a and imaginary part b.
   static Complex valueOf(double a, double b){
      Complex z = new Complex(a, b);
      return z;
   }

   // valueOf()
   // Return a new Complex with real part a and imaginary part 0.
   static Complex valueOf(double a){
      Complex z = new Complex(a, 0);
      return z;
   }

   // valueOf()
   // Return a new Complex constructed from s.
   static Complex valueOf(String s){
      Complex z = new Complex(s);
      return z;
   }

   // parseComplex()
   // Returns a double[] of length 2 containing (real, imaginary) parts
   // of a complex number represented by string argument str.  Throws a
   // NumberFormatException if str cannot be parsed as a complex number.
   static double[] parseComplex(String str){
      double[] part = new double[2];
      String s = str.trim();
      String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
      String SGN = "[+-]?";
      String OP =  "\\s*[+-]\\s*";
      String I =   "i";
      String OR =  "|";
      String REAL = SGN+NUM;
      String IMAG = SGN+NUM+"?"+I;
      String COMP = REAL+OR+
                    IMAG+OR+
                    REAL+OP+NUM+"?"+I;
      
      if( !s.matches(COMP) ){
         throw new NumberFormatException(
                   "Cannot parse input string \""+s+"\" as Complex");
      }
      s = s.replaceAll("\\s","");     
      if( s.matches(REAL) ){
         part[0] = Double.parseDouble(s);
         part[1] = 0;
      }else if( s.matches(SGN+I) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I, "1.0" ) );
      }else if( s.matches(IMAG) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I , "" ) );
      }else if( s.matches(REAL+OP+I) ){
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
      }else{   //  s.matches(REAL+OP+NUM+I) 
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
      }
      return part;
   }

}

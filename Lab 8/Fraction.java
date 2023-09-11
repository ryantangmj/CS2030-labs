 import java.util.Optional;

class Fraction extends AbstractNum<Frac> {
    Fraction(AbstractNum<Frac> abstractNum) {
        super(abstractNum.opt);
    }

    Fraction(Optional<Frac> f) {
        super(f);
    }

    static Fraction of(int n, int d) {
        if (!Num.of(n).isValid() || !Num.of(d).isValid()) {
            return new Fraction(Optional.<Frac>empty());
        } else if (Num.of(d).equals(Num.zero())) {
            return new Fraction(Optional.<Frac>empty());
        }

        return new Fraction(Optional.of(Frac.of(Num.of(n), Num.of(d))));
    }
    
    Fraction add(Fraction fraction) {
        if (!this.isValid() || !fraction.isValid()) {
            return this.isValid() ? fraction : this;
        }

        Optional<Num> a = opt.map(x -> x.first());
        Optional<Num> b = opt.map(x -> x.second());
        Optional<Num> c = fraction.opt.map(x -> x.first());
        Optional<Num> d = fraction.opt.map(x -> x.second());
                
        Optional<Num> ad = a.flatMap(x -> d.map(y -> x.mul(y)));
        Optional<Num> bc = b.flatMap(x -> c.map(y -> x.mul(y)));
        Optional<Num> adbc = ad.flatMap(x -> bc.map(y -> x.add(y)));
        Optional<Num> bd = b.flatMap(x -> d.map(y -> x.mul(y)));
        
        return adbc.map(x -> x.isValid()).equals(Optional.of(true))
            && bd.map(x -> x.isValid()).equals(Optional.of(true))
            ? new Fraction(adbc.flatMap(x -> bd.map(y -> Frac.of(x, y))))
                : new Fraction(Optional.<Frac>empty());
    }

    Fraction sub(Fraction fraction) {
        if (!this.isValid() || !fraction.isValid()) {
            return this.isValid() ? fraction : this;
        }

        Optional<Num> a = opt.map(x -> x.first());
        Optional<Num> b = opt.map(x -> x.second());
        Optional<Num> c = fraction.opt.map(x -> x.first());
        Optional<Num> d = fraction.opt.map(x -> x.second());

        Optional<Num> ad = a.flatMap(x -> d.map(y -> x.mul(y)));
        Optional<Num> bc = b.flatMap(x -> c.map(y -> x.mul(y)));
        Optional<Num> adbc = ad.flatMap(x -> bc.map(y -> x.sub(y)));
        Optional<Num> bd = b.flatMap(x -> d.map(y -> x.mul(y)));

        return adbc.map(x -> x.isValid()).equals(Optional.of(true))
            && bd.map(x -> x.isValid()).equals(Optional.of(true)) 
            ? new Fraction(adbc.flatMap(x -> bd.map(y -> Frac.of(x, y))))
                : new Fraction(Optional.<Frac>empty());
    }

    Fraction mul(Fraction fraction) {
        if (!this.isValid() || !fraction.isValid()) {
            return this.isValid() ? fraction : this;     
        }

        Optional<Num> a = opt.map(x -> x.first());
        Optional<Num> b = opt.map(x -> x.second());
        Optional<Num> c = fraction.opt.map(x -> x.first());
        Optional<Num> d = fraction.opt.map(x -> x.second());

        Optional<Num> ac = a.flatMap(x -> c.map(y -> x.mul(y)));
        Optional<Num> bd = b.flatMap(x -> d.map(y -> x.mul(y)));

        return ac.map(x -> x.isValid()).equals(Optional.of(true))
            && bd.map(x -> x.isValid()).equals(Optional.of(true))                             
            ? new Fraction(ac.flatMap(x -> bd.map(y -> Frac.of(x, y))))
                : new Fraction(Optional.<Frac>empty());
    }
}



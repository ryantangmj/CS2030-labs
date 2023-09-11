import java.util.Optional;
import java.util.stream.Stream;

class Num extends AbstractNum<Integer> {
    Num(AbstractNum<Integer> abstractNum) {
        super(abstractNum.opt);
    }

    Num(Optional<Integer> i) {
        super(i);
    }

    static Num of(int i) {
        return new Num(Optional.<Integer>of(i)
            .filter(AbstractNum.valid));
    }
    
    static Num zero() {
        return new Num(AbstractNum.zero());
    }
   
    static Num one() {
        return zero().succ();
    }

    Num succ() {
        return new Num(this.opt.map(s));
    }

    Num add(Num num) {
        if (!num.isValid() || !this.isValid()) {
            return num.isValid() ? new Num(this) : new Num(num);
        }

        return Stream.iterate(Num.zero(), n -> !n.equals(num), i -> i.succ())
            .reduce(this, (a,b) -> a.succ());

    }
    
    Num sub(Num num) {
        if (!num.isValid() || !this.isValid()) {
            return num.isValid() ? new Num(this) : new Num(num);
        }   
        
        Num output =  new Num(num.opt
            .map(n))
            .add(this); 
        
        return new Num(output.opt.filter(AbstractNum.valid));
    }

    Num mul(Num num) {
        if (!num.isValid() || !this.isValid()) {
            return num.isValid() ? new Num(this) : new Num(num);
        }
        
        return Stream.iterate(Num.zero(), n -> !n.equals(num), i -> i.succ())
            .reduce(Num.zero(), (a,b) -> a.add(this));
    }
}


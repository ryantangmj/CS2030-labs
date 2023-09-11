class Student extends KeyableMap<Module> {
    Student(String name) {
        super(name);
    }

    Student(String name, ImmutableMap<String, Module> modules) {
        super(name, modules);
    }
    
    @Override
    Student put(Module module) {
        return new Student(super.getKey(), super.getMap().put(module.getKey(), module));
    }
    
    /* 
    String getValue() {
        String output  = "";

        for (Map.Entry<String, Module> e: super.getMap().entrySet()) {
            output += String.format("%s, ",  e.getValue());
        }   
        
        return output.length() == 0 ? output : output.substring(0, output.length() - 2);
    } 

    @Override
    public String toString() {
        return String.format("%s: {%s}", super.getKey(), this.getValue());
    }
    */
    
}

class Module extends KeyableMap<Assessment> {
    Module(String module) {
        super(module);
    }

    Module(String module, ImmutableMap<String, Assessment> assessments) {
        super(module, assessments);
    }
    
    @Override
    Module put(Assessment assessment) {
        return new Module(super.getKey(), super.getMap().put(assessment.getKey(), assessment));
    }
    
    /* 
    String getValue() {
        String output  = "";

        for (Map.Entry<String, Assessment> e: super.getMap().entrySet()) {
            output += String.format("{%s, %s}, ", e.getKey(), e.getValue());
        }

        return output.length() == 0 ? output : output.substring(0, output.length() - 2);
    }

    @Override
    public String toString() {
        return String.format("%s: {%s}", super.getKey(), this.getValue());
    }
    */
    
}



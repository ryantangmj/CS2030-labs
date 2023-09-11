class Assessment implements Keyable {
    private final String assessment;
    private final String grade;

    Assessment(String assessment, String grade) {
        this.assessment = assessment;
        this.grade = grade;
    }

    @Override 
    public String getKey() {
        return assessment;
    }

    String getGrade() {
        return grade;
    }
    
    @Override
    public String toString() {
        return String.format("{%s: %s}", assessment, grade);
    }
}

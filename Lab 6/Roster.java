import java.util.Map;

class Roster extends KeyableMap<Student> {
    Roster(String year) {
        super(year);
    }

    Roster(String year, ImmutableMap<String, Student> students) {
        super(year, students);
    }
    
    @Override
    Roster put(Student student) {
        return new Roster(super.getKey(), super.getMap().put(student.getKey(), student));
    }

    String getGrade(String name, String mod, String assessment) {
        return this.get(name)
            .flatMap(x -> x.get(mod))
            .flatMap(x -> x.get(assessment))
            .map(x -> x.getGrade())
            .orElse(String.format("No such record: %s %s %s", name, mod, assessment));
    }

    Roster add(String name, String mod, String assess, String grade) {
        Student student = this.get(name).orElse(new Student(name));
        Module module = student.get(mod).orElse(new Module(mod));
        Assessment assessment = new Assessment(assess, grade);
        return this.put(student.put(module.put(assessment)));
    }
}
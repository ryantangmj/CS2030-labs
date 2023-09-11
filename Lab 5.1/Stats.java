class Stats {
    private final double waitTime;
    private final int success;
    private final int failure;

    public Stats() {
        this.waitTime = 0.0;
        this.success = 0;
        this.failure = 0;
    }

    public Stats(double waitTime, int success, int failure) {
        this.waitTime = waitTime;
        this.success = success;
        this.failure = failure;
    }

    Stats addSuccess() {
        return new Stats(this.waitTime, this.success + 1, this.failure); 
    }

    Stats addFailure() {
        return new Stats(this.waitTime, this.success, this.failure + 1);
    }

    Stats updateWaitTime(double extraTime) {
        return new Stats(this.waitTime + extraTime, this.success, this.failure);
    }

    double getWaitTime() {
        return this.waitTime;
    }

    int getSuccess() {
        return this.success;
    }

    int getFailure() {
        return this.failure;
    }
}

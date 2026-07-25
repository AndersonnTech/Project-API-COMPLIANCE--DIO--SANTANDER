package project.compliance.domain;

public record RiskAssessment(int score, RiskLevel riskLevel, RiskAssessmentStatus status) {
    public RiskAssessment(int score, RiskAssessmentStatus status) {
        this(score, detemineRiskLevel(score, status), status);
    }

    private static RiskLevel detemineRiskLevel(int score, RiskAssessmentStatus status) {
        if (status == RiskAssessmentStatus.REJECTED) return RiskLevel.CRITICAL;
        if (score > 70) return RiskLevel.HIGH;
        if (score > 30) return RiskLevel.MEDIUM;
        return RiskLevel.LOW;
    }
}

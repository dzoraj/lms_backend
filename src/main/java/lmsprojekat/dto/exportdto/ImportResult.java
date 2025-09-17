package lmsprojekat.dto.exportdto;

public class ImportResult {

	private Long evaluationId;
	private int attemptsTotal;
	private int inserted;
	private int updated;
	private int skipped;
	private boolean replaced;

	public ImportResult() {
	}

	public ImportResult(Long evaluationId, int attemptsTotal, int inserted, int updated, int skipped,
			boolean replaced) {
		this.evaluationId = evaluationId;
		this.attemptsTotal = attemptsTotal;
		this.inserted = inserted;
		this.updated = updated;
		this.skipped = skipped;
		this.replaced = replaced;
	}

	public Long getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(Long evaluationId) {
		this.evaluationId = evaluationId;
	}

	public int getAttemptsTotal() {
		return attemptsTotal;
	}

	public void setAttemptsTotal(int attemptsTotal) {
		this.attemptsTotal = attemptsTotal;
	}

	public int getInserted() {
		return inserted;
	}

	public void setInserted(int inserted) {
		this.inserted = inserted;
	}

	public int getUpdated() {
		return updated;
	}

	public void setUpdated(int updated) {
		this.updated = updated;
	}

	public int getSkipped() {
		return skipped;
	}

	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}

	public boolean isReplaced() {
		return replaced;
	}

	public void setReplaced(boolean replaced) {
		this.replaced = replaced;
	}
}
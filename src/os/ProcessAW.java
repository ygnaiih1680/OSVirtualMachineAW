package os;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Vector;

public class ProcessAW {
	int pid, main;
	public int[] code, data;
	public ActivationRecord[] stack;
	public Vector<Instance> heap;

	public ProcessAW(int pid, int[] code, int[] data, int size, int main) {
		this.pid = pid;
		this.main = main;
		this.code = code;
		this.data = data;
		this.stack = new ActivationRecord[size];
		this.heap = new Vector<>();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		ProcessAW processAW = (ProcessAW) o;

		return new EqualsBuilder()
				.append(pid, processAW.pid)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(pid)
				.toHashCode();
	}

	@Override
	public String toString() {
		return "ProcessAW {" + "pid=" + pid +
				'}';
	}
}
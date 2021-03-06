package os;

import pc.mainboard.MainBoard;
import pc.mainboard.cpu.Register;

import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.stream.IntStream;

class MemoryManagerAW {
	private Hashtable<Integer, Integer> memoryIndexTable;

	MemoryManagerAW() {
		this.memoryIndexTable = new Hashtable<>();
	}

	public void on(){
		OperatingSystem.uxManagerAW.updateMemory();
	}

	synchronized void load(ProcessAW processAW, int priority) {
		int bound = MainBoard.ram.memory.length;
		for (int i = 0; i < bound; i++) {
			if (MainBoard.ram.memory[i] == null) {
				this.memoryIndexTable.put(processAW.pid, i);
				MainBoard.ram.memory[i] = processAW;
				OperatingSystem.processManagerAW.newProcess(i, priority, processAW);
				OperatingSystem.uxManagerAW.updateMemory();
				return;
			}
		}
	}

	public ArrayList<ProcessAW> getLoadedProcess(){
		ArrayList<ProcessAW> processAWS = new ArrayList<>();
		for (Integer integer : this.memoryIndexTable.keySet()) {
			processAWS.add(MainBoard.ram.memory[this.memoryIndexTable.get(integer)]);
		}
		return processAWS;
	}

	synchronized void unload(int pid) {
		int index = this.memoryIndexTable.get(pid);
		this.memoryIndexTable.remove(pid);
		MainBoard.ram.memory[index] = null;
		System.gc();
		OperatingSystem.uxManagerAW.updateMemory();
	}

	int processAddress(int pid) {
		return this.memoryIndexTable.get(pid);
	}

	ProcessAW getProcess(int pid){
		return MainBoard.ram.memory[this.processAddress(pid)];
	}

}

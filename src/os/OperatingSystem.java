package os;

import pc.io.IODevice;

public class OperatingSystem {
	static final long TIME_SLICE = 15000000/*15000000-15ms*/;
	static ProcessManagerAW processManagerAW;
	static MemoryManagerAW memoryManagerAW;
	public static FileManagerAW fileManagerAW;
	static DeviceManagerAW deviceManagerAW;
	static InterruptServiceRoutine isr;

	public void on(){
		processManagerAW = new ProcessManagerAW();
		memoryManagerAW = new MemoryManagerAW();
		fileManagerAW = new FileManagerAW();
		deviceManagerAW = new DeviceManagerAW();
	}

	public void connect(IODevice device){

	}

}

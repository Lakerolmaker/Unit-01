package LakerLibrary;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.io.File;


public class systemInfo {

	private Runtime runtime = Runtime.getRuntime();
	private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	
	public static void main(String[] args) throws Exception {
		systemInfo info = new systemInfo();
		System.out.print("OS-name : " + info.getOSname());
		System.out.print("\n");
		System.out.print("OS-version : " + info.getOSversion());
		System.out.print("\n");
		System.out.print("OS-arch : " + info.getOsArch());
		System.out.print("\n");
		System.out.print("Max-mem : " + info.getVMMaxMem());
		System.out.print("\n");
		System.out.print("Alocated-mem : " + info.getVMTotalMem());
		System.out.print("\n");
		System.out.print("Used-mem : " + info.getUsedMem());
		System.out.print("\n");
		System.out.print("total free mem : " + info.getVMTotalFreeMem());
		System.out.print("\n");
		System.out.print("avaleble-cores : " + info.getAvailableCores());
		System.out.print("\n");
		System.out.print("\n");
	}
	
	
	public int getScreenWidth() {
		return gd.getDisplayMode().getWidth();
	}

	public int getScreenHeight() {
		return gd.getDisplayMode().getHeight();
	}

	public String getComputerName() {
		return System.getProperty("user.name");
	}

	// -- System general info Functions -- //
	public String getOSname() {
		return System.getProperty("os.name");
	}

	public String getOSversion() {
		return System.getProperty("os.version");
	}

	public String getOsArch() {
		return System.getProperty("os.arch");
	}

	// -- Ram Functions -- //
	public long getVMTotalMem() {
		return Runtime.getRuntime().totalMemory() / 1024;
	}

	public long getVMFreeMem() {
		return Runtime.getRuntime().freeMemory() / 1024;
	}

	public long getVMMaxMem() {
		return Runtime.getRuntime().maxMemory() / 1024;
	}

	public long getVMTotalFreeMem() {
		return getVMFreeMem() + (getVMMaxMem() - getVMTotalMem()) / 1024;
	}

	public long getTotallRam() {
		com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory
				.getOperatingSystemMXBean();
		return os.getTotalPhysicalMemorySize() / 1000000000;
	}

	public long getUsedMem() {
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
	}

	// -- Disk Functions -- //
	public int getAmountOfDrives() {
		File[] roots = File.listRoots();
		return roots.length;
	}

	public String getDiskname(int Disknumber) {
		File[] roots = File.listRoots();
		return roots[Disknumber].getAbsolutePath();
	}

	public long getTotallDiskSpace(int Disknumber) {
		File[] roots = File.listRoots();
		return roots[Disknumber].getTotalSpace() / 1000000000;
	}

	public long getAvalableDiskSpace(int Disknumber) {
		File[] roots = File.listRoots();
		return roots[Disknumber].getFreeSpace() / 1000000000;
	}

	public long getUsableDiskSpcae(int Disknumber) {
		File[] roots = File.listRoots();
		return roots[Disknumber].getUsableSpace() / 1000000000;
	}

	public int getDiskProcentage(int Disknumber) {
		double calculate = (1 - ((double) getAvalableDiskSpace(Disknumber) / (double) getTotallDiskSpace(Disknumber)))
				* 100;
		return (int) calculate;
	}

	public String getDiskPath(int Disknumber) {
		File[] roots = File.listRoots();
		return roots[Disknumber].getPath();
	}

	public boolean getNormalDisk(int Disknumber) {
		File[] roots = File.listRoots();
		return roots[Disknumber].canRead();
	}

	// -- CPU Functions -- //
	public int getAvailableCores() {
		return runtime.availableProcessors();
	}

	public double cpuLoad() {
		com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory
				.getOperatingSystemMXBean();
		return os.getSystemCpuLoad();
	}

	// -- Display -- //
	public int getNumberOfDisplays() {
		int returned = 0;
		try {
			// Get local graphics environment
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

			// Returns an array of all of the screen GraphicsDevice objects.
			GraphicsDevice[] devices = env.getScreenDevices();

			returned = devices.length;
		} catch (HeadlessException e) {
		}
		return returned;
	}

}
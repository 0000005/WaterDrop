package com.yin.waterdrop.spy.reporter;

import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import com.yin.waterdrop.rpc.entity.itf.HarddiskPartitionEntity;
import com.yin.waterdrop.rpc.entity.itf.SysInfoEntity;

public class SystemInfoGeter 
{
	public static SysInfoEntity getSystemInfo(Sigar sigar) throws SigarException
	{
		SysInfoEntity sysInfo = new SysInfoEntity();
		CpuInfo cpuInfo =sigar.getCpuInfoList()[0];
		
		//cpu
		sysInfo.setCpuVendor(cpuInfo.getVendor());
		sysInfo.setCpuMhz(cpuInfo.getMhz());
		sysInfo.setCpuModel(cpuInfo.getModel());
		sysInfo.setCpuTotal(cpuInfo.getTotalCores());
		
		//内存
		sysInfo.setRAMSize(sigar.getMem().getRam());
		
		//硬盘
		FileSystem [] fileSystem =sigar.getFileSystemList();
		List<HarddiskPartitionEntity> harddisk = new ArrayList<HarddiskPartitionEntity>();
		for (int i = 0; i < fileSystem.length; i++) 
		{
			HarddiskPartitionEntity partition = new HarddiskPartitionEntity();
			FileSystemUsage usage =sigar.getFileSystemUsage(fileSystem[i].getDirName());
			
			long used = usage.getTotal() - usage.getFree();
            
			partition.setTotalSize(usage.getTotal());
			partition.setUsedSize(used);
			partition.setMounted(fileSystem[i].getDirName());
			partition.setType(fileSystem[i].getSysTypeName());
			harddisk.add(partition);
		}
		
		//系统信息
		sysInfo.setOsName( System.getProperty("os.name"));
		sysInfo.setJdkInfo(System.getProperty("java.version")+" x"+System.getProperty("sun.arch.data.model"));
		
		return sysInfo;
	}
}

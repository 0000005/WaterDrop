package com.yin.waterdrop.spy.rpcImp;

import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yin.waterdrop.rpc.entity.itf.HarddiskPartitionEntity;
import com.yin.waterdrop.rpc.entity.itf.SysInfoEntity;
import com.yin.waterdrop.rpc.rpcInterface.SysInfoService;

public class SysInfoServiceImp implements SysInfoService 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public SysInfoEntity getSysInfo() 
	{

		Sigar sigar = new Sigar();
		SysInfoEntity sysInfo = new SysInfoEntity();
		try
		{
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
		}
		catch(Exception e)
		{
			logger.error("获取服务器配置信息失败",e);
		}
		return sysInfo;
	}

}

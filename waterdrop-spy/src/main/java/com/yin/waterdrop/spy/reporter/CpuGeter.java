package com.yin.waterdrop.spy.reporter;

import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import com.yin.waterdrop.rpc.entity.itf.CpuCoreEntity;

public class CpuGeter 
{
	public static List<CpuCoreEntity> getCurrCpuStatus(Sigar sigar) throws SigarException
	{
		List<CpuCoreEntity> cpus = new ArrayList<CpuCoreEntity>();
		CpuPerc[] cpuInfo =sigar.getCpuPercList();
		 
		for (int i = 0; i < cpuInfo.length; i++) 
		{
			CpuCoreEntity cpuCore = new CpuCoreEntity();
			cpuCore.setIndex(i);
			cpuCore.setCombined(cpuInfo[i].getCombined());
			cpuCore.setIdleTime(cpuInfo[i].getIdle());
			cpuCore.setNiceTime(cpuInfo[i].getNice());
			cpuCore.setSysTime(cpuInfo[i].getSys());
			cpuCore.setUserTime(cpuInfo[i].getUser());
			cpuCore.setWaitTime(cpuInfo[i].getWait());
			cpus.add(cpuCore);
		}
		
		return cpus;
	}
}

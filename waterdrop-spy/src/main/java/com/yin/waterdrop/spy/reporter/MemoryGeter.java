package com.yin.waterdrop.spy.reporter;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import com.yin.waterdrop.rpc.entity.itf.MemoryEntity;

public class MemoryGeter 
{
	public static MemoryEntity getCurrMemory(Sigar sigar) throws SigarException
	{
		Mem sigarMem =sigar.getMem();
		MemoryEntity memory = new MemoryEntity();
		memory.setFreeSize(sigarMem.getActualFree());
		memory.setUsedSize(sigarMem.getActualUsed());
		return memory;
	}
}

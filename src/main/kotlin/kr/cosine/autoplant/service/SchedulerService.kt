package kr.cosine.autoplant.service

import kotlinx.coroutines.Runnable
import kr.hqservice.framework.global.core.component.Service
import org.bukkit.plugin.Plugin

@Service
class SchedulerService(
    private val plugin: Plugin
) {

    private val scheduler = plugin.server.scheduler

    fun runTaskLater(delay: Long = 1L, runnable: Runnable) {
        scheduler.runTaskLater(plugin, runnable, delay)
    }
}
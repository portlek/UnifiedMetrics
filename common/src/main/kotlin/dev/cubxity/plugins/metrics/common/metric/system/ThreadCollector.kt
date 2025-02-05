/*
 *     UnifiedMetrics is a fully-featured metrics collection plugin for Minecraft servers.
 *     Copyright (C) 2021  Cubxity
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.cubxity.plugins.metrics.common.metric.system

import dev.cubxity.plugins.metrics.api.metric.collector.MetricCollector
import dev.cubxity.plugins.metrics.api.metric.data.CounterSample
import dev.cubxity.plugins.metrics.api.metric.data.GaugeSample
import dev.cubxity.plugins.metrics.api.metric.data.MetricSample
import java.lang.management.ManagementFactory

class ThreadCollector : MetricCollector {
    private val bean = ManagementFactory.getThreadMXBean()

    override fun collect(): List<MetricSample> = listOf(
        GaugeSample("jvm_threads_current_count", bean.threadCount),
        GaugeSample("jvm_threads_daemon_count", bean.daemonThreadCount),
        CounterSample("jvm_threads_started_total", bean.totalStartedThreadCount),
        GaugeSample("jvm_threads_peak", bean.peakThreadCount),
    )
}
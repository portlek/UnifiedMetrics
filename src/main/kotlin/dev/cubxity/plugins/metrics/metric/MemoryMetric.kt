/*
 *     UnifiedMetrics is a fully-featured metrics collection plugin for Spigot.
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

package dev.cubxity.plugins.metrics.metric

import dev.cubxity.plugins.metrics.UnifiedMetrics
import dev.cubxity.plugins.metrics.measurement.MemoryMeasurement

class MemoryMetric : Metric<MemoryMeasurement> {
    override val isSync: Boolean
        get() = false

    override fun getMeasurements(plugin: UnifiedMetrics): List<MemoryMeasurement> {
        val server = plugin.metricsConfig.influx.server
        val runtime = Runtime.getRuntime()
        val total = runtime.totalMemory()
        val used = total - runtime.freeMemory()

        return listOf(MemoryMeasurement(server, used, total, runtime.maxMemory()))
    }
}
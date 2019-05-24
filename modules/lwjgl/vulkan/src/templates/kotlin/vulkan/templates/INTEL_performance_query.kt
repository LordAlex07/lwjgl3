/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package vulkan.templates

import org.lwjgl.generator.*
import vulkan.*

val INTEL_performance_query = "INTELPerformanceQuery".nativeClassVK("INTEL_performance_query", type = "device", postfix = INTEL) {
    documentation =
        """
        This extension allows an application to capture performance data to be interpreted by a external application or library.

        Such a library is available at : https://github.com/intel/metrics-discovery

        Performance analysis tools such as GPA (https://software.intel.com/en-us/gpa) make use of this extension and the metrics-discovery library to present the data in a human readable way.

        <h5>Example Code</h5>
        <pre><code>
￿// A previously created device
￿VkDevice device;
￿
￿// A queue from from device
￿VkQueue queue;
￿
￿VkInitializePerformanceApiInfoINTEL performanceApiInfoIntel = {
￿  VK_STRUCTURE_TYPE_INITIALIZE_PERFORMANCE_API_INFO_INTEL,
￿  NULL,
￿  NULL
￿};
￿
￿vkInitializePerformanceApiINTEL(
￿  device,
￿  &amp;performanceApiInfoIntel);
￿
￿VkQueryPoolCreateInfoINTEL queryPoolIntel = {
￿  VK_STRUCTURE_TYPE_QUERY_POOL_CREATE_INFO_INTEL,
￿  NULL,
￿  VK_QUERY_POOL_SAMPLING_MODE_MANUAL_INTEL,
￿};
￿
￿VkQueryPoolCreateInfo queryPoolCreateInfo = {
￿  VK_STRUCTURE_TYPE_QUERY_POOL_CREATE_INFO,
￿  &amp;queryPoolIntel,
￿  0,
￿  VK_QUERY_TYPE_PERFORMANCE_QUERY_INTEL,
￿  1,
￿  0
￿};
￿
￿VkQueryPool queryPool;
￿
￿VkResult result = vkCreateQueryPool(
￿  device,
￿  &amp;queryPoolCreateInfo,
￿  NULL,
￿  &amp;queryPool);
￿
￿assert(VK_SUCCESS == result);
￿
￿// A command buffer we want to record counters on
￿VkCommandBuffer commandBuffer;
￿
￿VkCommandBufferBeginInfo commandBufferBeginInfo = {
￿  VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO,
￿  NULL,
￿  VK_COMMAND_BUFFER_USAGE_ONE_TIME_SUBMIT_BIT,
￿  NULL
￿};
￿
￿result = vkBeginCommandBuffer(commandBuffer, &amp;commandBufferBeginInfo);
￿
￿assert(VK_SUCCESS == result);
￿
￿vkCmdResetQueryPool(
￿  commandBuffer,
￿  queryPool,
￿  0,
￿  1);
￿
￿vkCmdBeginQuery(
￿  commandBuffer,
￿  queryPool,
￿  0,
￿  0);
￿
￿// Perform the commands you want to get performance information on
￿// ...
￿
￿// Perform a barrier to ensure all previous commands were complete before
￿// ending the query
￿vkCmdPipelineBarrier(commandBuffer,
￿  VK_PIPELINE_STAGE_BOTTOM_OF_PIPE_BIT,
￿  VK_PIPELINE_STAGE_BOTTOM_OF_PIPE_BIT,
￿  0,
￿  0,
￿  NULL,
￿  0,
￿  NULL,
￿  0,
￿  NULL);
￿
￿vkCmdEndQuery(
￿  commandBuffer,
￿  queryPool,
￿  0);
￿
￿result = vkEndCommandBuffer(commandBuffer);
￿
￿assert(VK_SUCCESS == result);
￿
￿VkPerformanceConfigurationAcquireInfoINTEL performanceConfigurationAcquireInfo = {
￿  VK_STRUCTURE_TYPE_PERFORMANCE_CONFIGURATION_ACQUIRE_INFO_INTEL,
￿  NULL,
￿  VK_PERFORMANCE_CONFIGURATION_TYPE_COMMAND_QUEUE_METRICS_DISCOVERY_ACTIVATED_INTEL
￿};
￿
￿VkPerformanceConfigurationINTEL performanceConfigurationIntel;
￿
￿result = vkAcquirePerformanceConfigurationINTEL(
￿  device,
￿  &amp;performanceConfigurationAcquireInfo,
￿  &amp;performanceConfigurationIntel);
￿
￿vkQueueSetPerformanceConfigurationINTEL(queue, performanceConfigurationIntel);
￿
￿assert(VK_SUCCESS == result);
￿
￿// Submit the command buffer and wait for its completion
￿// ...
￿
￿result = vkReleasePerformanceConfigurationINTEL(
￿  device,
￿  performanceConfigurationIntel);
￿
￿assert(VK_SUCCESS == result);
￿
￿// Get the report size from metrics-discovery's QueryReportSize
￿
￿result = vkGetQueryPoolResults(
￿  device,
￿  queryPool,
￿  0, 1, QueryReportSize,
￿  data, QueryReportSize, 0);
￿
￿assert(VK_SUCCESS == result);
￿
￿// The data can then be passed back to metrics-discovery from which
￿// human readable values can be queried.</code></pre>

        <dl>
            <dt><b>Name String</b></dt>
            <dd>{@code VK_INTEL_performance_query}</dd>

            <dt><b>Extension Type</b></dt>
            <dd>Device extension</dd>

            <dt><b>Registered Extension Number</b></dt>
            <dd>211</dd>

            <dt><b>Revision</b></dt>
            <dd>1</dd>

            <dt><b>Extension and Version Dependencies</b></dt>
            <dd><ul>
                <li>Requires Vulkan 1.0</li>
            </ul></dd>

            <dt><b>Contact</b></dt>
            <dd><ul>
                <li>Lionel Landwerlin <a target="_blank" href="https://github.com/KhronosGroup/Vulkan-Docs/issues/new?title=VK_INTEL_performance_query:%20&amp;body=@llandwerlin%20">llandwerlin</a></li>
            </ul></dd>

            <dt><b>Last Modified Date</b></dt>
            <dd>2018-05-16</dd>

            <dt><b>IP Status</b></dt>
            <dd>No known IP claims.</dd>

            <dt><b>Contributors</b></dt>
            <dd><ul>
                <li>Lionel Landwerlin, Intel</li>
                <li>Piotr Maciejewski, Intel</li>
            </ul></dd>
        </dl>
        """

    IntConstant(
        "The extension specification version.",

        "INTEL_PERFORMANCE_QUERY_SPEC_VERSION".."1"
    )

    StringConstant(
        "The extension name.",

        "INTEL_PERFORMANCE_QUERY_EXTENSION_NAME".."VK_INTEL_performance_query"
    )

    EnumConstant(
        "Extends {@code VkStructureType}.",

        "STRUCTURE_TYPE_QUERY_POOL_CREATE_INFO_INTEL".."1000210000",
        "STRUCTURE_TYPE_INITIALIZE_PERFORMANCE_API_INFO_INTEL".."1000210001",
        "STRUCTURE_TYPE_PERFORMANCE_MARKER_INFO_INTEL".."1000210002",
        "STRUCTURE_TYPE_PERFORMANCE_STREAM_MARKER_INFO_INTEL".."1000210003",
        "STRUCTURE_TYPE_PERFORMANCE_OVERRIDE_INFO_INTEL".."1000210004",
        "STRUCTURE_TYPE_PERFORMANCE_CONFIGURATION_ACQUIRE_INFO_INTEL".."1000210005"
    )

    EnumConstant(
        "Extends {@code VkQueryType}.",

        "QUERY_TYPE_PERFORMANCE_QUERY_INTEL".."1000210000"
    )

    EnumConstant(
        "Extends {@code VkObjectType}.",

        "OBJECT_TYPE_PERFORMANCE_CONFIGURATION_INTEL".."1000210000"
    )

    EnumConstant(
        """
        VkPerformanceConfigurationTypeINTEL - Type of performance configuration

        <h5>See Also</h5>
        ##VkPerformanceConfigurationAcquireInfoINTEL

        <h5>Document Notes</h5>
        For more information, see the Vulkan Specification at URL

        https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html#VkPerformanceConfigurationTypeINTEL

        This page is extracted from the Vulkan Specification. Fixes and changes should be made to the Specification, not directly.
        """,

        "PERFORMANCE_CONFIGURATION_TYPE_COMMAND_QUEUE_METRICS_DISCOVERY_ACTIVATED_INTEL".."0"
    )

    EnumConstant(
        """
        VkQueryPoolSamplingModeINTEL - Enum specifying how performance queries should be captured

        <h5>Description</h5>
        <ul>
            <li>#QUERY_POOL_SAMPLING_MODE_MANUAL_INTEL is the default mode in which the application calls #CmdBeginQuery() and #CmdEndQuery() to record performance data.</li>
        </ul>

        <h5>See Also</h5>
        ##VkQueryPoolCreateInfoINTEL
        """,

        "QUERY_POOL_SAMPLING_MODE_MANUAL_INTEL".."0"
    )

    EnumConstant(
        """
        VkPerformanceOverrideTypeINTEL - Performance override type

        <h5>Description</h5>
        <ul>
            <li>#PERFORMANCE_OVERRIDE_TYPE_NULL_HARDWARE_INTEL turns all rendering operations into noop.</li>
            <li>#PERFORMANCE_OVERRIDE_TYPE_FLUSH_GPU_CACHES_INTEL stalls the stream of commands until all previously emitted commands have completed and all caches been flushed and invalidated.</li>
        </ul>

        <h5>See Also</h5>
        ##VkPerformanceOverrideInfoINTEL
        """,

        "PERFORMANCE_OVERRIDE_TYPE_NULL_HARDWARE_INTEL".."0",
        "PERFORMANCE_OVERRIDE_TYPE_FLUSH_GPU_CACHES_INTEL".."1"
    )

    EnumConstant(
        """
        VkPerformanceParameterTypeINTEL - Parameters that can be queried

        <h5>Description</h5>
        <ul>
            <li>#PERFORMANCE_PARAMETER_TYPE_HW_COUNTERS_SUPPORTED_INTEL has a boolean result which tells whether hardware counters can be captured.</li>
            <li>#PERFORMANCE_PARAMETER_TYPE_STREAM_MARKER_VALID_BITS_INTEL has a 32 bits integer result which tells how many bits can be written into the {@code VkStreamPerformanceMarkerInfoINTEL} value.</li>
        </ul>

        <h5>See Also</h5>
        #GetPerformanceParameterINTEL()
        """,

        "PERFORMANCE_PARAMETER_TYPE_HW_COUNTERS_SUPPORTED_INTEL".."0",
        "PERFORMANCE_PARAMETER_TYPE_STREAM_MARKER_VALID_BITS_INTEL".."1"
    )

    EnumConstant(
        """
        VkPerformanceValueTypeINTEL - Type of the parameters that can be queried

        <h5>See Also</h5>
        ##VkPerformanceValueINTEL

        <h5>Document Notes</h5>
        For more information, see the Vulkan Specification at URL

        https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html#VkPerformanceValueTypeINTEL

        This page is extracted from the Vulkan Specification. Fixes and changes should be made to the Specification, not directly.
        """,

        "PERFORMANCE_VALUE_TYPE_UINT32_INTEL".."0",
        "PERFORMANCE_VALUE_TYPE_UINT64_INTEL".."1",
        "PERFORMANCE_VALUE_TYPE_FLOAT_INTEL".."2",
        "PERFORMANCE_VALUE_TYPE_BOOL_INTEL".."3",
        "PERFORMANCE_VALUE_TYPE_STRING_INTEL".."4"
    )

    VkResult(
        "InitializePerformanceApiINTEL",
        """
        Initialize a device for performance queries.

        <h5>C Specification</h5>
        Prior to creating a performance query pool, initialize the device for performance queries with the call:

        <pre><code>
￿VkResult vkInitializePerformanceApiINTEL(
￿    VkDevice                                    device,
￿    const VkInitializePerformanceApiInfoINTEL*  pInitializeInfo);</code></pre>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
            <li>{@code pInitializeInfo} <b>must</b> be a valid pointer to a valid ##VkInitializePerformanceApiInfoINTEL structure</li>
        </ul>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>

        <h5>See Also</h5>
        ##VkInitializePerformanceApiInfoINTEL
        """,

        VkDevice("device", "the logical device used for the queries."),
        VkInitializePerformanceApiInfoINTEL.const.p("pInitializeInfo", "a pointer to the initialization parameters.")
    )

    void(
        "UninitializePerformanceApiINTEL",
        """
        Uninitialize a device for performance queries.

        <h5>C Specification</h5>
        Once performance query operations have completed, uninitalize the device for performance queries with the call:

        <pre><code>
￿void vkUninitializePerformanceApiINTEL(
￿    VkDevice                                    device);</code></pre>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
        </ul>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>
        """,

        VkDevice("device", "the logical device used for the queries.")
    )

    VkResult(
        "CmdSetPerformanceMarkerINTEL",
        """
        Markers.

        <h5>C Specification</h5>
        To help associate query results with a particular point at which an application emitted commands, markers can be set into the command buffers with the call:

        <pre><code>
￿VkResult vkCmdSetPerformanceMarkerINTEL(
￿    VkCommandBuffer                             commandBuffer,
￿    const VkPerformanceMarkerInfoINTEL*         pMarkerInfo);</code></pre>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code commandBuffer} <b>must</b> be a valid {@code VkCommandBuffer} handle</li>
            <li>{@code pMarkerInfo} <b>must</b> be a valid pointer to a valid ##VkPerformanceMarkerInfoINTEL structure</li>
            <li>{@code commandBuffer} <b>must</b> be in the <a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#commandbuffers-lifecycle">recording state</a></li>
            <li>The {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> support graphics, compute, or transfer operations</li>
        </ul>

        <h5>Host Synchronization</h5>
        <ul>
            <li>Host access to the {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> be externally synchronized</li>
        </ul>

        <h5>Command Properties</h5>
        <table class="lwjgl">
            <thead><tr><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#VkCommandBufferLevel">Command Buffer Levels</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#vkCmdBeginRenderPass">Render Pass Scope</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#VkQueueFlagBits">Supported Queue Types</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#synchronization-pipeline-stages-types">Pipeline Type</a></th></tr></thead>
            <tbody><tr><td>Primary Secondary</td><td>Both</td><td>Graphics Compute Transfer</td><td></td></tr></tbody>
        </table>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>

        <h5>See Also</h5>
        ##VkPerformanceMarkerInfoINTEL
        """,

        VkCommandBuffer("commandBuffer", ""),
        VkPerformanceMarkerInfoINTEL.const.p("pMarkerInfo", "")
    )

    VkResult(
        "CmdSetPerformanceStreamMarkerINTEL",
        """
        Markers.

        <h5>C Specification</h5>
        When monitoring the behavior of an application wihtin the dataset generated by the entire set of applications running on the system, it is useful to identify draw calls within a potentially huge amount of performance data. To do so, application can generate stream markers that will be used to trace back a particular draw call with a particular performance data item.

        <pre><code>
￿VkResult vkCmdSetPerformanceStreamMarkerINTEL(
￿    VkCommandBuffer                             commandBuffer,
￿    const VkPerformanceStreamMarkerInfoINTEL*   pMarkerInfo);</code></pre>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code commandBuffer} <b>must</b> be a valid {@code VkCommandBuffer} handle</li>
            <li>{@code pMarkerInfo} <b>must</b> be a valid pointer to a valid ##VkPerformanceStreamMarkerInfoINTEL structure</li>
            <li>{@code commandBuffer} <b>must</b> be in the <a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#commandbuffers-lifecycle">recording state</a></li>
            <li>The {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> support graphics, compute, or transfer operations</li>
        </ul>

        <h5>Host Synchronization</h5>
        <ul>
            <li>Host access to the {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> be externally synchronized</li>
        </ul>

        <h5>Command Properties</h5>
        <table class="lwjgl">
            <thead><tr><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#VkCommandBufferLevel">Command Buffer Levels</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#vkCmdBeginRenderPass">Render Pass Scope</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#VkQueueFlagBits">Supported Queue Types</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#synchronization-pipeline-stages-types">Pipeline Type</a></th></tr></thead>
            <tbody><tr><td>Primary Secondary</td><td>Both</td><td>Graphics Compute Transfer</td><td></td></tr></tbody>
        </table>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>

        <h5>See Also</h5>
        ##VkPerformanceStreamMarkerInfoINTEL
        """,

        VkCommandBuffer("commandBuffer", ""),
        VkPerformanceStreamMarkerInfoINTEL.const.p("pMarkerInfo", "")
    )

    VkResult(
        "CmdSetPerformanceOverrideINTEL",
        """
        Performance override settings.

        <h5>C Specification</h5>
        Some applications might want measure the effect of a set of commands with a different settings. It is possible to override a particular settings using :

        <pre><code>
￿VkResult vkCmdSetPerformanceOverrideINTEL(
￿    VkCommandBuffer                             commandBuffer,
￿    const VkPerformanceOverrideInfoINTEL*       pOverrideInfo);</code></pre>

        <h5>Valid Usage</h5>
        <ul>
            <li>{@code pOverrideInfo} <b>must</b> not be used with a {@code VkPerformanceOverrideTypeINTEL} that is not reported available by {@code vkGetPerformanceParameterINTEL}.</li>
        </ul>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code commandBuffer} <b>must</b> be a valid {@code VkCommandBuffer} handle</li>
            <li>{@code pOverrideInfo} <b>must</b> be a valid pointer to a valid ##VkPerformanceOverrideInfoINTEL structure</li>
            <li>{@code commandBuffer} <b>must</b> be in the <a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#commandbuffers-lifecycle">recording state</a></li>
            <li>The {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> support graphics, compute, or transfer operations</li>
        </ul>

        <h5>Host Synchronization</h5>
        <ul>
            <li>Host access to the {@code VkCommandPool} that {@code commandBuffer} was allocated from <b>must</b> be externally synchronized</li>
        </ul>

        <h5>Command Properties</h5>
        <table class="lwjgl">
            <thead><tr><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#VkCommandBufferLevel">Command Buffer Levels</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#vkCmdBeginRenderPass">Render Pass Scope</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#VkQueueFlagBits">Supported Queue Types</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#synchronization-pipeline-stages-types">Pipeline Type</a></th></tr></thead>
            <tbody><tr><td>Primary Secondary</td><td>Both</td><td>Graphics Compute Transfer</td><td></td></tr></tbody>
        </table>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>

        <h5>See Also</h5>
        ##VkPerformanceOverrideInfoINTEL
        """,

        VkCommandBuffer("commandBuffer", "the command buffer where the override takes place."),
        VkPerformanceOverrideInfoINTEL.const.p("pOverrideInfo", "a pointer to a ##VkPerformanceOverrideInfoINTEL selecting the parameter to override.")
    )

    VkResult(
        "AcquirePerformanceConfigurationINTEL",
        """
        Acquire the performance query capability.

        <h5>C Specification</h5>
        To acquire a device performance configuration, call:

        <pre><code>
￿VkResult vkAcquirePerformanceConfigurationINTEL(
￿    VkDevice                                    device,
￿    const VkPerformanceConfigurationAcquireInfoINTEL* pAcquireInfo,
￿    VkPerformanceConfigurationINTEL*            pConfiguration);</code></pre>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
            <li>{@code pAcquireInfo} <b>must</b> be a valid pointer to a valid ##VkPerformanceConfigurationAcquireInfoINTEL structure</li>
            <li>{@code pConfiguration} <b>must</b> be a valid pointer to a {@code VkPerformanceConfigurationINTEL} handle</li>
        </ul>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>

        <h5>See Also</h5>
        ##VkPerformanceConfigurationAcquireInfoINTEL
        """,

        VkDevice("device", "the logical device that the performance query commands will be submitted to."),
        VkPerformanceConfigurationAcquireInfoINTEL.const.p("pAcquireInfo", "a pointer to ##VkPerformanceConfigurationAcquireInfoINTEL."),
        Check(1)..VkPerformanceConfigurationINTEL.p("pConfiguration", "a pointer to a {@code VkPerformanceConfigurationINTEL} handle in which the resulting configuration object is returned.")
    )

    VkResult(
        "ReleasePerformanceConfigurationINTEL",
        """
        Release a configuration to capture performance data.

        <h5>C Specification</h5>
        To release a device performance configuration, call:

        <pre><code>
￿VkResult vkReleasePerformanceConfigurationINTEL(
￿    VkDevice                                    device,
￿    VkPerformanceConfigurationINTEL             configuration);</code></pre>

        <h5>Valid Usage</h5>
        <ul>
            <li>{@code configuration} <b>must</b> not be released before all command buffers submitted while the configuration was set are in <a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#commandbuffers-lifecycle">pending state</a>.</li>
        </ul>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
            <li>{@code configuration} <b>must</b> be a valid {@code VkPerformanceConfigurationINTEL} handle</li>
            <li>{@code configuration} <b>must</b> have been created, allocated, or retrieved from {@code device}</li>
        </ul>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>
        """,

        VkDevice("device", "the device associated to the configuration object to release."),
        VkPerformanceConfigurationINTEL("configuration", "the configuration object to release.")
    )

    VkResult(
        "QueueSetPerformanceConfigurationINTEL",
        """
        Set a performance query.

        <h5>C Specification</h5>
        To set a performance configuration, call:

        <pre><code>
￿VkResult vkQueueSetPerformanceConfigurationINTEL(
￿    VkQueue                                     queue,
￿    VkPerformanceConfigurationINTEL             configuration);</code></pre>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code queue} <b>must</b> be a valid {@code VkQueue} handle</li>
            <li>{@code configuration} <b>must</b> be a valid {@code VkPerformanceConfigurationINTEL} handle</li>
            <li>Both of {@code configuration}, and {@code queue} <b>must</b> have been created, allocated, or retrieved from the same {@code VkDevice}</li>
        </ul>

        <h5>Command Properties</h5>
        <table class="lwjgl">
            <thead><tr><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#VkCommandBufferLevel">Command Buffer Levels</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#vkCmdBeginRenderPass">Render Pass Scope</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#VkQueueFlagBits">Supported Queue Types</a></th><th><a target="_blank" href="https://www.khronos.org/registry/vulkan/specs/1.1-extensions/html/vkspec.html\#synchronization-pipeline-stages-types">Pipeline Type</a></th></tr></thead>
            <tbody><tr><td>-</td><td>-</td><td>Any</td><td>-</td></tr></tbody>
        </table>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>
        """,

        VkQueue("queue", "the queue on which the configuration will be used."),
        VkPerformanceConfigurationINTEL("configuration", "the configuration to use.")
    )

    VkResult(
        "GetPerformanceParameterINTEL",
        """
        Query performance capabilities of the device.

        <h5>C Specification</h5>
        Some performance query features of a device can be discovered with the call:

        <pre><code>
￿VkResult vkGetPerformanceParameterINTEL(
￿    VkDevice                                    device,
￿    VkPerformanceParameterTypeINTEL             parameter,
￿    VkPerformanceValueINTEL*                    pValue);</code></pre>

        <h5>Valid Usage (Implicit)</h5>
        <ul>
            <li>{@code device} <b>must</b> be a valid {@code VkDevice} handle</li>
            <li>{@code parameter} <b>must</b> be a valid {@code VkPerformanceParameterTypeINTEL} value</li>
            <li>{@code pValue} <b>must</b> be a valid pointer to a ##VkPerformanceValueINTEL structure</li>
        </ul>

        <h5>Return Codes</h5>
        <dl>
            <dt>On success, this command returns</dt>
            <dd><ul>
                <li>#SUCCESS</li>
            </ul></dd>

            <dt>On failure, this command returns</dt>
            <dd><ul>
                <li>#ERROR_TOO_MANY_OBJECTS</li>
                <li>#ERROR_OUT_OF_HOST_MEMORY</li>
            </ul></dd>
        </dl>

        <h5>See Also</h5>
        ##VkPerformanceValueINTEL
        """,

        VkDevice("device", "the logical device to query."),
        VkPerformanceParameterTypeINTEL("parameter", "the parameter to query."),
        VkPerformanceValueINTEL.p("pValue", "points to an instance of the ##VkPerformanceValueINTEL structure in which the type and value of the parameter are returned.")
    )
}
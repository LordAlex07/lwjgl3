/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.opengl.templates

import org.lwjgl.generator.*
import org.lwjgl.opengl.*

val ARB_compute_variable_group_size = "ARBComputeVariableGroupSize".nativeClassGL("ARB_compute_variable_group_size", postfix = ARB) {
	nativeImport (
		"OpenGL.h"
	)

	documentation =
		"""
		Native bindings to the $registryLink extension.

		This extension allows applications to write generic compute shaders that operate on work groups with arbitrary dimensions. Instead of specifying a fixed
		work group size in the compute shader, an application can use a compute shader using the {@code local_size_variable} layout qualifer to indicate a
		variable work group size. When using such compute shaders, the new command DispatchComputeGroupSizeARB should be used to specify both a work group size
		and work group count.

		In this extension, compute shaders with fixed group sizes must be dispatched by GL43#DispatchCompute() and GL43#DispatchComputeIndirect(). Compute
		shaders with variable group sizes must be dispatched via #DispatchComputeGroupSizeARB(). No support is provided in this extension for indirect dispatch
		of compute shaders with a variable group size.

		Requires ${GL43.core} or ${ARB_compute_shader.link}.
		"""

	IntConstant(
		"Accepted by the {@code pname} parameter of GetIntegerv, GetBooleanv, GetFloatv, GetDoublev and GetInteger64v.",

		"MAX_COMPUTE_VARIABLE_GROUP_INVOCATIONS_ARB" _ 0x9344,
		"MAX_COMPUTE_FIXED_GROUP_INVOCATIONS_ARB" _ 0x90EB
	)

	IntConstant(
		"Accepted by the {@code pname} parameter of GetIntegeri_v, GetBooleani_v, GetFloati_v, GetDoublei_v and GetInteger64i_v.",

		"MAX_COMPUTE_VARIABLE_GROUP_SIZE_ARB" _ 0x9345,
		"MAX_COMPUTE_FIXED_GROUP_SIZE_ARB" _ 0x91BF
	)

	val src = GL43["DispatchCompute"]
	void(
		"DispatchComputeGroupSizeARB",
		"""
		Launches one or more compute work groups, with arbitrary dimensions.
		
		An GL11#INVALID_OPERATION error is generated by DispatchComputeGroupSizeARB if the active program for the compute shader stage has a fixed work group
		size.

		An GL11#INVALID_VALUE error is generated by DispatchComputeGroupSizeARB if any of {@code group_size_x}, {@code group_size_y}, or {@code group_size_z} is
		less than or equal to zero or greater than the maximum local work group size for compute shaders with variable group size
		(#MAX_COMPUTE_VARIABLE_GROUP_SIZE_ARB) in the corresponding dimension.

		An GL11#INVALID_VALUE error is generated by DispatchComputeGroupSizeARB if the product of {@code group_size_x}, {@code group_size_y}, and
		{@code group_size_z} exceeds the implementation-dependent maximum local work group invocation count for compute shaders with variable group size
		(#MAX_COMPUTE_VARIABLE_GROUP_INVOCATIONS_ARB).
		""",

		src["num_groups_x"],
		src["num_groups_y"],
		src["num_groups_z"],
		GLuint.IN("group_size_x", "the group size in the X dimension"),
		GLuint.IN("group_size_y", "the group size in the Y dimension"),
		GLuint.IN("group_size_z", "the group size in the Z dimension")
	)
}
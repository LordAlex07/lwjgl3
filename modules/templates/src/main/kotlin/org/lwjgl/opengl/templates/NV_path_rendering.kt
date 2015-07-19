/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.opengl.templates

import org.lwjgl.generator.*
import org.lwjgl.generator.opengl.AutoType
import org.lwjgl.generator.opengl.BufferType.*
import org.lwjgl.opengl.*

val NV_path_rendering = "NVPathRendering".nativeClassGL("NV_path_rendering", postfix = NV) {
	nativeImport (
		"OpenGL.h"
	)

	documentation =
		"""
		Native bindings to the $registryLink extension.

		Conventional OpenGL supports rendering images (pixel rectangles and bitmaps) and simple geometric primitives (points, lines, polygons).

		This extension adds a new rendering paradigm, known as path rendering, for rendering filled and stroked paths. Path rendering is not novel but rather a
		standard part of most resolution-independent 2D rendering systems such as Flash, PDF, Silverlight, SVG, Java 2D, Office drawings, TrueType fonts,
		PostScript and its fonts, Quartz 2D, XML Paper Specification (XPS), and OpenVG. What is novel is the ability to mix path rendering with arbitrary
		OpenGL 3D rendering and imaging.

		With this extension, path rendering becomes a first-class rendering mode within the OpenGL graphics system that can be arbitrarily mixed with existing
		OpenGL rendering and can take advantage of OpenGL's existing mechanisms for texturing, programmability, and per-fragment operations.

		Unlike geometric primitive rendering, paths are specified on a 2D (non-projective) plane rather than in 3D (projective) space. Even though the path is
		defined in a 2D plane, every path can be transformed into 3D clip space allowing for 3D view frustum & user-defined clipping, depth offset, and depth
		testing in the same manner as geometric primitive rendering.

		Both geometric primitive rendering and path rendering support rasterization of edges defined by line segments; however, path rendering also allows path
		segments to be specified by Bezier (cubic or quadratic) curves or partial elliptical arcs. This allows path rendering to define truly curved primitive
		boundaries unlike the straight edges of line and polygon primitives. Whereas geometric primitive rendering requires convex polygons for well-defined
		rendering results, path rendering allows (and encourages!) concave and curved outlines to be specified. These paths are even allowed to self-intersect.

		When filling closed paths, the winding of paths (counterclockwise or clockwise) determines whether pixels are inside or outside of the path.

		Paths can also be stroked whereby, conceptually, a fixed-width "brush" is pulled along the path such that the brush remains orthogonal to the gradient
		of each path segment. Samples within the sweep of this brush are considered inside the stroke of the path.

		This extension supports path rendering through a sequence of three operations:
		${ol(
			"""
			Path specification is the process of creating and updating a path object consisting of a set of path commands and a corresponding set of 2D
			vertices.

			Path commands can be specified explicitly from path command and coordinate data, parsed from a string based on standard grammars for representing
			paths, or specified by a particular glyph of standard font representations. Also new paths can be specified by weighting one or more existing paths
			so long as all the weighted paths have consistent command sequences.

			Each path object contains zero or more subpaths specified by a sequence of line segments, partial elliptical arcs, and (cubic or quadratic) Bezier
			curve segments. Each path may contain multiple subpaths that can be closed (forming a contour) or open.
			""",
		    """
		    Path stenciling is the process of updating the stencil buffer based on a path's coverage transformed into window space.

			Path stenciling can determine either the filled or stroked coverage of a path.

			The details of path stenciling are explained within the core of the specification.

			Stenciling a stroked path supports all the standard embellishments for path stroking such as end caps, join styles, miter limits, dashing, and dash
			caps. These stroking properties specified are parameters of path objects.
		    """,
		    """
		    Path covering is the process of emitting simple (convex & planar) geometry that (conservatively) "covers" the path's sample coverage in the stencil
			buffer. During path covering, stencil testing can be configured to discard fragments not within the actual coverage of the path as determined by
			prior path stenciling.

			Path covering can cover either the filled or stroked coverage of a path.

			The details of path covering are explained within the core of the specification.
		    """
		)}
		To render a path object into the color buffer, an application specifies a path object and then uses a two-step rendering process. First, the path
		object is stenciled whereby the path object's stroked or filled coverage is rasterized into the stencil buffer. Second, the path object is covered
		whereby conservative bounding geometry for the path is transformed and rasterized with stencil testing configured to test against the coverage
		information written to the stencil buffer in the first step so that only fragments covered by the path are written during this second step. Also during
		this second step written pixels typically have their stencil value reset (so there's no need for clearing the stencil buffer between rendering each
		path).

        Requires ${registryLinkTo("NV", "path_rendering")}.
		"""

	IntConstant(
		"Accepted in elements of the {@code commands} array parameter of PathCommandsNV and PathSubCommandsNV.",

		"CLOSE_PATH_NV" _ 0x00,
		"MOVE_TO_NV" _ 0x02,
		"RELATIVE_MOVE_TO_NV" _ 0x03,
		"LINE_TO_NV" _ 0x04,
		"RELATIVE_LINE_TO_NV" _ 0x05,
		"HORIZONTAL_LINE_TO_NV" _ 0x06,
		"RELATIVE_HORIZONTAL_LINE_TO_NV" _ 0x07,
		"VERTICAL_LINE_TO_NV" _ 0x08,
		"RELATIVE_VERTICAL_LINE_TO_NV" _ 0x09,
		"QUADRATIC_CURVE_TO_NV" _ 0x0A,
		"RELATIVE_QUADRATIC_CURVE_TO_NV" _ 0x0B,
		"CUBIC_CURVE_TO_NV" _ 0x0C,
		"RELATIVE_CUBIC_CURVE_TO_NV" _ 0x0D,
		"SMOOTH_QUADRATIC_CURVE_TO_NV" _ 0x0E,
		"RELATIVE_SMOOTH_QUADRATIC_CURVE_TO_NV" _ 0x0F,
		"SMOOTH_CUBIC_CURVE_TO_NV" _ 0x10,
		"RELATIVE_SMOOTH_CUBIC_CURVE_TO_NV" _ 0x11,
		"SMALL_CCW_ARC_TO_NV" _ 0x12,
		"RELATIVE_SMALL_CCW_ARC_TO_NV" _ 0x13,
		"SMALL_CW_ARC_TO_NV" _ 0x14,
		"RELATIVE_SMALL_CW_ARC_TO_NV" _ 0x15,
		"LARGE_CCW_ARC_TO_NV" _ 0x16,
		"RELATIVE_LARGE_CCW_ARC_TO_NV" _ 0x17,
		"LARGE_CW_ARC_TO_NV" _ 0x18,
		"RELATIVE_LARGE_CW_ARC_TO_NV" _ 0x19,
		"CONIC_CURVE_TO_NV" _ 0x1A,
		"RELATIVE_CONIC_CURVE_TO_NV" _ 0x1B,
		"ROUNDED_RECT_NV" _ 0xE8,
		"RELATIVE_ROUNDED_RECT_NV" _ 0xE9,
		"ROUNDED_RECT2_NV" _ 0xEA,
		"RELATIVE_ROUNDED_RECT2_NV" _ 0xEB,
		"ROUNDED_RECT4_NV" _ 0xEC,
		"RELATIVE_ROUNDED_RECT4_NV" _ 0xED,
		"ROUNDED_RECT8_NV" _ 0xEE,
		"RELATIVE_ROUNDED_RECT8_NV" _ 0xEF,
		"RESTART_PATH_NV" _ 0xF0,
		"DUP_FIRST_CUBIC_CURVE_TO_NV" _ 0xF2,
		"DUP_LAST_CUBIC_CURVE_TO_NV" _ 0xF4,
		"RECT_NV" _ 0xF6,
		"RELATIVE_RECT_NV" _ 0xF7,
		"CIRCULAR_CCW_ARC_TO_NV" _ 0xF8,
		"CIRCULAR_CW_ARC_TO_NV" _ 0xFA,
		"CIRCULAR_TANGENT_ARC_TO_NV" _ 0xFC,
		"ARC_TO_NV" _ 0xFE,
		"RELATIVE_ARC_TO_NV" _ 0xFF
	)

	IntConstant(
		"Accepted by the {@code format} parameter of PathStringNV.",

		"PATH_FORMAT_SVG_NV" _ 0x9070,
		"PATH_FORMAT_PS_NV" _ 0x9071
	)

	IntConstant(
		"Accepted by the {@code fontTarget} parameter of PathGlyphsNV, PathGlyphRangeNV, and PathGlyphIndexRangeNV.",

		"STANDARD_FONT_NAME_NV" _ 0x9072,
		"SYSTEM_FONT_NAME_NV" _ 0x9073,
		"FILE_NAME_NV" _ 0x9074
	)

	IntConstant(
		"Accepted by the {@code fontTarget} parameter of PathMemoryGlyphIndexArrayNV.",

		"STANDARD_FONT_FORMAT_NV" _ 0x936C
	)

	IntConstant(
		"Accepted by the {@code handleMissingGlyph} parameter of PathGlyphsNV and PathGlyphRangeNV.",

		"SKIP_MISSING_GLYPH_NV" _ 0x90A9,
		"USE_MISSING_GLYPH_NV" _ 0x90AA
	)

	IntConstant(
		"Returned by PathGlyphIndexRangeNV.",

		"FONT_GLYPHS_AVAILABLE_NV" _ 0x9368,
		"FONT_TARGET_UNAVAILABLE_NV" _ 0x9369,
		"FONT_UNAVAILABLE_NV" _ 0x936A,
		"FONT_UNINTELLIGIBLE_NV" _ 0x936B
	)

	val PathParameters = IntConstant(
		"""
		Accepted by the {@code pname} parameter of PathParameterfNV, PathParameterfvNV, GetPathParameterfvNV, PathParameteriNV, PathParameterivNV, and
		GetPathParameterivNV.
		""",

		"PATH_STROKE_WIDTH_NV" _ 0x9075,
		"PATH_INITIAL_END_CAP_NV" _ 0x9077,
		"PATH_TERMINAL_END_CAP_NV" _ 0x9078,
		"PATH_JOIN_STYLE_NV" _ 0x9079,
		"PATH_MITER_LIMIT_NV" _ 0x907A,
		"PATH_INITIAL_DASH_CAP_NV" _ 0x907C,
		"PATH_TERMINAL_DASH_CAP_NV" _ 0x907D,
		"PATH_DASH_OFFSET_NV" _ 0x907E,
		"PATH_CLIENT_LENGTH_NV" _ 0x907F,
		"PATH_DASH_OFFSET_RESET_NV" _ 0x90B4,
		"PATH_FILL_MODE_NV" _ 0x9080,
		"PATH_FILL_MASK_NV" _ 0x9081,
		"PATH_FILL_COVER_MODE_NV" _ 0x9082,
		"PATH_STROKE_COVER_MODE_NV" _ 0x9083,
		"PATH_STROKE_MASK_NV" _ 0x9084,
		"PATH_STROKE_BOUND_NV" _ 0x9086
	).javaDocLinks

	val PathParametersf = IntConstant(
		"Accepted by the {@code pname} parameter of PathParameterfNV and PathParameterfvNV.",

		"PATH_END_CAPS_NV" _ 0x9076,
		"PATH_DASH_CAPS_NV" _ 0x907B
	).javaDocLinks

	IntConstant(
		"Accepted by the {@code fillMode} parameter of StencilFillPathNV and StencilFillPathInstancedNV.",

		"COUNT_UP_NV" _ 0x9088,
		"COUNT_DOWN_NV" _ 0x9089
	)

	IntConstant(
		"Accepted by the {@code color} parameter of PathColorGenNV, GetPathColorGenivNV, and GetPathColorGenfvNV.",

		"PRIMARY_COLOR_NV" _ 0x852C,
		"SECONDARY_COLOR_NV" _ 0x852D
	)

	IntConstant(
		"Accepted by the {@code genMode} parameter of PathColorGenNV, PathTexGenNV, ProgramPathFragmentInputGenNV.",

		"PATH_OBJECT_BOUNDING_BOX_NV" _ 0x908A
	)

	IntConstant(
		"Accepted by the {@code coverMode} parameter of CoverFillPathNV and CoverFillPathInstancedNV.",

		"CONVEX_HULL_NV" _ 0x908B,
		"BOUNDING_BOX_NV" _ 0x908D
	)

	val TransformTypes = IntConstant(
		"""
		Accepted by the {@code transformType} parameter of StencilFillPathInstancedNV, StencilStrokePathInstancedNV, CoverFillPathInstancedNV, and
		CoverStrokePathInstancedNV.
		""",

		"TRANSLATE_X_NV" _ 0x908E,
		"TRANSLATE_Y_NV" _ 0x908F,
		"TRANSLATE_2D_NV" _ 0x9090,
		"TRANSLATE_3D_NV" _ 0x9091,
		"AFFINE_2D_NV" _ 0x9092,
		"AFFINE_3D_NV" _ 0x9094,
		"TRANSPOSE_AFFINE_2D_NV" _ 0x9096,
		"TRANSPOSE_AFFINE_3D_NV" _ 0x9098
	).javaDocLinks

	IntConstant(
		"""
		Accepted by the {@code type} or {@code pathNameType} parameter of StencilFillPathInstancedNV, StencilStrokePathInstancedNV, CoverFillPathInstancedNV,
		CoverStrokePathInstancedNV, GetPathMetricsNV, and GetPathSpacingNV.
		""",

		"UTF8_NV" _ 0x909A,
		"UTF16_NV" _ 0x909B
	)

	IntConstant(
		"Accepted by the {@code coverMode} parameter of CoverFillPathInstancedNV.",

		"BOUNDING_BOX_OF_BOUNDING_BOXES_NV" _ 0x909C
	)

	val GetPathParameters = IntConstant(
		"Accepted by the {@code pname} parameter of GetPathParameterfvNV and GetPathParameterivNV.",

		"PATH_COMMAND_COUNT_NV" _ 0x909D,
		"PATH_COORD_COUNT_NV" _ 0x909E,
		"PATH_DASH_ARRAY_COUNT_NV" _ 0x909F,
		"PATH_COMPUTED_LENGTH_NV" _ 0x90A0,
		"PATH_FILL_BOUNDING_BOX_NV" _ 0x90A1,
		"PATH_STROKE_BOUNDING_BOX_NV" _ 0x90A2
	)

	IntConstant(
		"""
		Accepted by the {@code value} parameter of PathParameterfNV, PathParameterfvNV, PathParameteriNV, and PathParameterivNV when {@code pname} is one of
		PATH_END_CAPS_NV, PATH_INTIAL_END_CAP_NV, PATH_TERMINAL_END_CAP_NV, PATH_DASH_CAPS_NV, PATH_INITIAL_DASH_CAP_NV, and PATH_TERMINAL_DASH_CAP_NV.
		""",

		"SQUARE_NV" _ 0x90A3,
		"ROUND_NV" _ 0x90A4,
		"TRIANGULAR_NV" _ 0x90A5
	)

	IntConstant(
		"""
		Accepted by the {@code value} parameter of PathParameterfNV, PathParameterfvNV, PathParameteriNV, and PathParameterivNV when {@code pname} is
		PATH_JOIN_STYLE_NV.
		""",

		"BEVEL_NV" _ 0x90A6,
		"MITER_REVERT_NV" _ 0x90A7,
		"MITER_TRUNCATE_NV" _ 0x90A8
	)

	IntConstant(
		"""
		Accepted by the {@code value} parameter of PathParameterfNV, PathParameterfvNV, PathParameteriNV, and PathParameterivNV when {@code pname} is
		PATH_DASH_OFFSET_RESET_NV.
		""",

		"MOVE_TO_RESETS_NV" _ 0x90B5,
		"MOVE_TO_CONTINUES_NV" _ 0x90B6
	)

	IntConstant(
		"Accepted by the {@code fontStyle} parameter of PathGlyphsNV, PathGlyphRangeNV, and PathGlyphIndexRangeNV.",

		"BOLD_BIT_NV" _ 0x01,
		"ITALIC_BIT_NV" _ 0x02
	)

	IntConstant(
		"Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv, and GetDoublev.",

		"PATH_ERROR_POSITION_NV" _ 0x90AB,
		"PATH_FOG_GEN_MODE_NV" _ 0x90AC,
		"PATH_STENCIL_FUNC_NV" _ 0x90B7,
		"PATH_STENCIL_REF_NV" _ 0x90B8,
		"PATH_STENCIL_VALUE_MASK_NV" _ 0x90B9,
		"PATH_STENCIL_DEPTH_OFFSET_FACTOR_NV" _ 0x90BD,
		"PATH_STENCIL_DEPTH_OFFSET_UNITS_NV" _ 0x90BE,
		"PATH_COVER_DEPTH_FUNC_NV" _ 0x90BF
	)

	val MetricQueryMask = IntConstant(
		"Accepted as a bit within the {@code metricQueryMask} parameter of GetPathMetricRangeNV or GetPathMetricsNV.",

		"GLYPH_WIDTH_BIT_NV" _ 0x01,
		"GLYPH_HEIGHT_BIT_NV" _ 0x02,
		"GLYPH_HORIZONTAL_BEARING_X_BIT_NV" _ 0x04,
		"GLYPH_HORIZONTAL_BEARING_Y_BIT_NV" _ 0x08,
		"GLYPH_HORIZONTAL_BEARING_ADVANCE_BIT_NV" _ 0x10,
		"GLYPH_VERTICAL_BEARING_X_BIT_NV" _ 0x20,
		"GLYPH_VERTICAL_BEARING_Y_BIT_NV" _ 0x40,
		"GLYPH_VERTICAL_BEARING_ADVANCE_BIT_NV" _ 0x80,
		"GLYPH_HAS_KERNING_BIT_NV" _ 0x100,
		"FONT_X_MIN_BOUNDS_BIT_NV" _ 0x00010000,
		"FONT_Y_MIN_BOUNDS_BIT_NV" _ 0x00020000,
		"FONT_X_MAX_BOUNDS_BIT_NV" _ 0x00040000,
		"FONT_Y_MAX_BOUNDS_BIT_NV" _ 0x00080000,
		"FONT_UNITS_PER_EM_BIT_NV" _ 0x00100000,
		"FONT_ASCENDER_BIT_NV" _ 0x00200000,
		"FONT_DESCENDER_BIT_NV" _ 0x00400000,
		"FONT_HEIGHT_BIT_NV" _ 0x00800000,
		"FONT_MAX_ADVANCE_WIDTH_BIT_NV" _ 0x01000000,
		"FONT_MAX_ADVANCE_HEIGHT_BIT_NV" _ 0x02000000,
		"FONT_UNDERLINE_POSITION_BIT_NV" _ 0x04000000,
		"FONT_UNDERLINE_THICKNESS_BIT_NV" _ 0x08000000,
		"FONT_HAS_KERNING_BIT_NV" _ 0x10000000,
		"FONT_NUM_GLYPH_INDICES_BIT_NV" _ 0x20000000
	).javaDocLinks

	val PathListModes = IntConstant(
		"Accepted by the {@code pathListMode} parameter of GetPathSpacingNV.",

		"ACCUM_ADJACENT_PAIRS_NV" _ 0x90AD,
		"ADJACENT_PAIRS_NV" _ 0x90AE,
		"FIRST_TO_REST_NV" _ 0x90AF
	).javaDocLinks

	IntConstant(
		"Accepted by the {@code pname} parameter of GetPathColorGenivNV, GetPathColorGenfvNV, GetPathTexGenivNV and GetPathTexGenfvNV.",

		"PATH_GEN_MODE_NV" _ 0x90B0,
		"PATH_GEN_COEFF_NV" _ 0x90B1
	)

	IntConstant(
		"Accepted by the {@code pname} parameter of GetPathColorGenivNV and GetPathColorGenfvNV.",

		"PATH_GEN_COLOR_FORMAT_NV" _ 0x90B2
	)

	IntConstant(
		"Accepted by the {@code pname} parameter of GetPathTexGenivNV and GetPathTexGenfvNV.",

		"PATH_GEN_COMPONENTS_NV" _ 0x90B3
	)

	IntConstant(
		"""
		Accepted by the {@code programInterface} parameter of GetProgramInterfaceiv, GetProgramResourceIndex, GetProgramResourceName, GetProgramResourceiv,
		GetProgramResourcefvNV, and GetProgramResourceLocation.
		""",

		"FRAGMENT_INPUT_NV" _ 0x936D
	)

	IntConstant(
		"Token values for matrices.",

		"PATH_PROJECTION_NV" _ 0x1701,
		"PATH_MODELVIEW_NV" _ 0x1700,
		"PATH_MODELVIEW_STACK_DEPTH_NV" _ 0x0BA3,
		"PATH_MODELVIEW_MATRIX_NV" _ 0x0BA6,
		"PATH_MAX_MODELVIEW_STACK_DEPTH_NV" _ 0x0D36,
		"PATH_TRANSPOSE_MODELVIEW_MATRIX_NV" _ 0x84E3,
		"PATH_PROJECTION_STACK_DEPTH_NV" _ 0x0BA4,
		"PATH_PROJECTION_MATRIX_NV" _ 0x0BA7,
		"PATH_MAX_PROJECTION_STACK_DEPTH_NV" _ 0x0D38,
		"PATH_TRANSPOSE_PROJECTION_MATRIX_NV" _ 0x84E4
	)

	void(
		"PathCommandsNV",
		"",

		GLuint.IN("path", ""),
		AutoSize("commands") _ GLsizei.IN("numCommands", ""),
		const _ GLubyte_p.IN("commands", ""),
		AutoSize("coords") _ GLsizei.IN("numCoords", ""),
		AutoType("coords", GL_BYTE, GL_SHORT, GL_FLOAT) _ GLenum.IN("coordType", "", "GL11#BYTE GL11#UNSIGNED_BYTE GL11#SHORT GL11#UNSIGNED_SHORT GL11#FLOAT"),
		const _ void_p.IN("coords", "")
	)

	void(
		"PathCoordsNV",
		"",

		GLuint.IN("path", ""),
		this["PathCommandsNV"]["numCoords"],
		this["PathCommandsNV"]["coordType"],
		this["PathCommandsNV"]["coords"]
	)

	void(
		"PathSubCommandsNV",
		"",

		GLuint.IN("path", ""),
		GLsizei.IN("commandStart", ""),
		GLsizei.IN("commandsToDelete", ""),
		AutoSize("commands") _ GLsizei.IN("numCommands", ""),
		const _ GLubyte_p.IN("commands", ""),
		this["PathCommandsNV"]["numCoords"],
		this["PathCommandsNV"]["coordType"],
		this["PathCommandsNV"]["coords"]
	)

	void(
		"PathSubCoordsNV",
		"",

		GLuint.IN("path", ""),
		GLsizei.IN("coordStart", ""),
		this["PathCommandsNV"]["numCoords"],
		this["PathCommandsNV"]["coordType"],
		this["PathCommandsNV"]["coords"]
	)

	void(
		"PathStringNV",
		"",

		GLuint.IN("path", ""),
		GLenum.IN("format", "", "#PATH_FORMAT_SVG_NV #PATH_FORMAT_PS_NV"),
		AutoSize("pathString") _ GLsizei.IN("length", ""),
		const _ void_p.IN("pathString", "")
	)

	void(
		"PathGlyphsNV",
		"",

		GLuint.IN("firstPathName", ""),
		GLenum.IN("fontTarget", "", "#STANDARD_FONT_NAME_NV #SYSTEM_FONT_NAME_NV #FILE_NAME_NV"),
		nullTerminated _ const _ void_p.IN("fontName", ""),
		GLbitfield.IN("fontStyle", "", "#BOLD_BIT_NV #ITALIC_BIT_NV", LinkMode.BITFIELD),
		GLsizei.IN("numGlyphs", ""),
		GLenum.IN("type", "", "GL11#UNSIGNED_BYTE GL11#UNSIGNED_SHORT GL11#UNSIGNED_INT #UTF8_NV #UTF16_NV GL11#2_BYTES GL11#3_BYTES GL11#4_BYTES"),
		Check("numGlyphs") _ const _ void_p.IN("charcodes", ""),
		GLenum.IN("handleMissingGlyphs", "", "#SKIP_MISSING_GLYPH_NV #USE_MISSING_GLYPH_NV"),
		GLuint.IN("pathParameterTemplate", ""),
		GLfloat.IN("emScale", "")
	)

	void(
		"PathGlyphRangeNV",
		"",

		this["PathGlyphsNV"]["firstPathName"],
		this["PathGlyphsNV"]["fontTarget"],
		this["PathGlyphsNV"]["fontName"],
		this["PathGlyphsNV"]["fontStyle"],
		GLuint.IN("firstGlyph", ""),
		this["PathGlyphsNV"]["numGlyphs"],
		this["PathGlyphsNV"]["handleMissingGlyphs"],
		this["PathGlyphsNV"]["pathParameterTemplate"],
		this["PathGlyphsNV"]["emScale"]
	)

	ignoreMissing _ GLenum(
		"PathGlyphIndexArrayNV",
		"",

		this["PathGlyphsNV"]["firstPathName"],
		this["PathGlyphsNV"]["fontTarget"],
		this["PathGlyphsNV"]["fontName"],
		this["PathGlyphsNV"]["fontStyle"],
		GLuint.IN("firstGlyphIndex", ""),
		this["PathGlyphsNV"]["numGlyphs"],
		this["PathGlyphsNV"]["pathParameterTemplate"],
		this["PathGlyphsNV"]["emScale"]
	)

	ignoreMissing _ GLenum(
		"PathMemoryGlyphIndexArrayNV",
		"",

		this["PathGlyphsNV"]["firstPathName"],
		this["PathGlyphsNV"]["fontTarget"],
		AutoSize("fontData") _ GLsizeiptr.IN("fontSize", ""),
		const _ void_p.IN("fontData", ""),
		GLsizei.IN("faceIndex", ""),
		this["PathGlyphIndexArrayNV"]["firstGlyphIndex"],
		this["PathGlyphsNV"]["numGlyphs"],
		this["PathGlyphsNV"]["pathParameterTemplate"],
		this["PathGlyphsNV"]["emScale"]
	)

	void(
		"CopyPathNV",
		"",

		GLuint.IN("resultPath", ""),
		GLuint.IN("srcPath", "")
	)

	void(
		"InterpolatePathsNV",
		"",

		GLuint.IN("resultPath", ""),
		GLuint.IN("pathA", ""),
		GLuint.IN("pathB", ""),
		GLfloat.IN("weight", "")
	)

	void(
		"TransformPathNV",
		"",

		GLuint.IN("resultPath", ""),
		GLuint.IN("srcPath", ""),
		GLenum.IN("transformType", "", TransformTypes),
		Check(16) _ const _ GLfloat_p.IN("transformValues", "")
	)

	void(
		"PathParameterivNV",
		"",

		GLuint.IN("path", ""),
		GLenum.IN("pname", "", PathParameters),
		Check(1) _ const _ GLint_p.IN("value", "")
	)

	void(
		"PathParameteriNV",
		"",

		GLuint.IN("path", ""),
		this["PathParameterivNV"]["pname"],
		GLint.IN("value", "")
	)

	void(
		"PathParameterfvNV",
		"",

		GLuint.IN("path", ""),
		GLenum.IN("pname", "", "$PathParameters $PathParametersf"),
		const _ GLfloat_p.IN("value", "")
	)

	void(
		"PathParameterfNV",
		"",

		GLuint.IN("path", ""),
		this["PathParameterfvNV"]["pname"],
		GLfloat.IN("value", "")
	)

	void(
		"PathDashArrayNV",
		"",

		GLuint.IN("path", ""),
		AutoSize("dashArray") _ GLsizei.IN("dashCount", ""),
		const _ GLfloat_p.IN("dashArray", "")
	)

	GLuint(
		"GenPathsNV",
		"",

		GLsizei.IN("range", "")
	)

	void(
		"DeletePathsNV",
		"",

		GLuint.IN("path", ""),
		GLsizei.IN("range", "")
	)

	GLboolean(
		"IsPathNV",
		"",

		GLuint.IN("path", "")
	)

	void(
		"PathStencilFuncNV",
		"",

		GLenum.IN("func", ""),
		GLint.IN("ref", ""),
		GLuint.IN("mask", "")
	)

	void(
		"PathStencilDepthOffsetNV",
		"",

		GLfloat.IN("factor", ""),
		GLfloat.IN("units", "")
	)

	void(
		"StencilFillPathNV",
		"",

		GLuint.IN("path", ""),
		GLenum.IN("fillMode", "", "GL11#INVERT #COUNT_UP_NV #COUNT_DOWN_NV #PATH_FILL_MODE_NV"),
		GLuint.IN("mask", "")
	)

	void(
		"StencilStrokePathNV",
		"",

		GLuint.IN("path", ""),
		GLint.IN("reference", ""),
		GLuint.IN("mask", "")
	)

	void(
		"StencilFillPathInstancedNV",
		"",

		GLsizei.IN("numPaths", ""),
		GLenum.IN(
			"pathNameType",
			"",
			"GL11#BYTE GL11#UNSIGNED_BYTE GL11#SHORT GL11#UNSIGNED_SHORT GL11#INT GL11#UNSIGNED_INT GL11#FLOAT #UTF8_NV #UTF16_NV GL11#2_BYTES GL11#3_BYTES GL11#4_BYTES"
		),
		Check("numPaths") _ const _ void_p.IN("paths", ""),
		GLuint.IN("pathBase", ""),
		this["StencilFillPathNV"]["fillMode"],
		GLuint.IN("mask", ""),
		this["TransformPathNV"]["transformType"],
		this["TransformPathNV"]["transformValues"]
	)

	void(
		"StencilStrokePathInstancedNV",
		"",

		this["StencilFillPathInstancedNV"]["numPaths"],
		this["StencilFillPathInstancedNV"]["pathNameType"],
		this["StencilFillPathInstancedNV"]["paths"],
		this["StencilFillPathInstancedNV"]["pathBase"],
		GLint.IN("reference", ""),
		this["StencilFillPathInstancedNV"]["mask"],
		this["StencilFillPathInstancedNV"]["transformType"],
		this["StencilFillPathInstancedNV"]["transformValues"]
	)

	void(
		"PathCoverDepthFuncNV",
		"",

		GLenum.IN("zfunc", "")
	)

	ignoreMissing _ void(
		"PathColorGenNV",
		"",

		GLenum.IN("color", "", "GL13#PRIMARY_COLOR #PRIMARY_COLOR_NV #SECONDARY_COLOR_NV"),
		GLenum.IN("genMode", "", "GL11#NONE GL11#OBJECT_LINEAR #PATH_OBJECT_BOUNDING_BOX_NV GL11#EYE_LINEAR GL13#CONSTANT"),
		GLenum.IN("colorFormat", ""),
		const _ GLfloat_p.IN("coeffs", "")
	)

	ignoreMissing _ void(
		"PathTexGenNV",
		"",

		GLenum.IN("texCoordSet", ""),
		GLenum.IN("genMode", ""),
		GLint.IN("components", ""),
		const _ GLfloat_p.IN("coeffs", "")
	)

	ignoreMissing _ void(
		"PathFogGenNV",
		"",

		GLenum.IN("genMode", "")
	)

	void(
		"CoverFillPathNV",
		"",

		GLuint.IN("path", ""),
		GLenum.IN("coverMode", "", "#CONVEX_HULL_NV #BOUNDING_BOX_NV")
	)

	void(
		"CoverStrokePathNV",
		"",

		GLuint.IN("path", ""),
		this["CoverFillPathNV"]["coverMode"]
	)

	void(
		"CoverFillPathInstancedNV",
		"",

		this["StencilFillPathInstancedNV"]["numPaths"],
		this["StencilFillPathInstancedNV"]["pathNameType"],
		this["StencilFillPathInstancedNV"]["paths"],
		this["StencilFillPathInstancedNV"]["pathBase"],
		GLenum.IN("coverMode", "", "#CONVEX_HULL_NV #BOUNDING_BOX_NV #BOUNDING_BOX_OF_BOUNDING_BOXES_NV"),
		this["TransformPathNV"]["transformType"],
		this["TransformPathNV"]["transformValues"]
	)

	void(
		"CoverStrokePathInstancedNV",
		"",

		this["StencilFillPathInstancedNV"]["numPaths"],
		this["StencilFillPathInstancedNV"]["pathNameType"],
		this["StencilFillPathInstancedNV"]["paths"],
		this["StencilFillPathInstancedNV"]["pathBase"],
		this["CoverFillPathInstancedNV"]["coverMode"],
		this["TransformPathNV"]["transformType"],
		this["TransformPathNV"]["transformValues"]
	)

	ignoreMissing _ void(
		"StencilThenCoverFillPathNV",
		"",

		GLuint.IN("path", ""),
		this["StencilFillPathNV"]["fillMode"],
		GLuint.IN("mask", ""),
		this["CoverFillPathNV"]["coverMode"]
	)

	ignoreMissing _ void(
		"StencilThenCoverStrokePathNV",
		"",

		GLuint.IN("path", ""),
		GLint.IN("reference", ""),
		GLuint.IN("mask", ""),
		this["CoverFillPathNV"]["coverMode"]
	)

	ignoreMissing _ void(
		"StencilThenCoverFillPathInstancedNV",
		"",

		this["StencilFillPathInstancedNV"]["numPaths"],
		this["StencilFillPathInstancedNV"]["pathNameType"],
		this["StencilFillPathInstancedNV"]["paths"],
		this["StencilFillPathInstancedNV"]["pathBase"],
		this["StencilFillPathNV"]["fillMode"],
		GLuint.IN("mask", ""),
		this["CoverFillPathInstancedNV"]["coverMode"],
		this["TransformPathNV"]["transformType"],
		this["TransformPathNV"]["transformValues"]
	)

	ignoreMissing _ void(
		"StencilThenCoverStrokePathInstancedNV",
		"",

		this["StencilFillPathInstancedNV"]["numPaths"],
		this["StencilFillPathInstancedNV"]["pathNameType"],
		this["StencilFillPathInstancedNV"]["paths"],
		this["StencilFillPathInstancedNV"]["pathBase"],
		GLint.IN("reference", ""),
		GLuint.IN("mask", ""),
		this["CoverFillPathInstancedNV"]["coverMode"],
		this["TransformPathNV"]["transformType"],
		this["TransformPathNV"]["transformValues"]
	)

	ignoreMissing _ GLenum(
		"PathGlyphIndexRangeNV",
		"",

		GLenum.IN("fontTarget", ""),
		const _ void_p.IN("fontName", ""),
		GLbitfield.IN("fontStyle", ""),
		GLuint.IN("pathParameterTemplate", ""),
		GLfloat.IN("emScale", ""),
		GLuint.IN("baseAndCount", "")
	)

	ignoreMissing _ void(
		"ProgramPathFragmentInputGenNV",
		"",

		GLuint.IN("program", ""),
		GLint.IN("location", ""),
		GLenum.IN("genMode", ""),
		GLint.IN("components", ""),
		const _ GLfloat_p.IN("coeffs", "")
	)

	void(
		"GetPathParameterivNV",
		"",

		GLuint.IN("path", ""),
		GLenum.IN("pname", "", "$PathParameters $GetPathParameters"),
		returnValue _ Check(1) _ GLint_p.OUT("value", "")
	)

	void(
		"GetPathParameterfvNV",
		"",

		GLuint.IN("path", ""),
		this["GetPathParameterivNV"]["pname"],
		returnValue _ Check(1) _ GLfloat_p.OUT("value", "")
	)

	void(
		"GetPathCommandsNV",
		"",

		GLuint.IN("path", ""),
		GLubyte_p.OUT("commands", "")
	)

	void(
		"GetPathCoordsNV",
		"",

		GLuint.IN("path", ""),
		GLfloat_p.OUT("coords", "")
	)

	void(
		"GetPathDashArrayNV",
		"",

		GLuint.IN("path", ""),
		GLfloat_p.OUT("dashArray", "")
	)

	void(
		"GetPathMetricsNV",
		"",

		GLbitfield.IN("metricQueryMask", "", MetricQueryMask, LinkMode.BITFIELD),
		this["StencilFillPathInstancedNV"]["numPaths"],
		this["StencilFillPathInstancedNV"]["pathNameType"],
		this["StencilFillPathInstancedNV"]["paths"],
		this["StencilFillPathInstancedNV"]["pathBase"],
		GLsizei.IN("stride", ""),
		GLfloat_p.OUT("metrics", "")
	)

	void(
		"GetPathMetricRangeNV",
		"",

		this["GetPathMetricsNV"]["metricQueryMask"],
		GLuint.IN("firstPathName", ""),
		GLsizei.IN("numPaths", ""),
		this["GetPathMetricsNV"]["stride"],
		this["GetPathMetricsNV"]["metrics"]
	)

	void(
		"GetPathSpacingNV",
		"",

		GLenum.IN("pathListMode", "", PathListModes),
		this["StencilFillPathInstancedNV"]["numPaths"],
		this["StencilFillPathInstancedNV"]["pathNameType"],
		this["StencilFillPathInstancedNV"]["paths"],
		this["StencilFillPathInstancedNV"]["pathBase"],
		GLfloat.IN("advanceScale", ""),
		GLfloat.IN("kerningScale", ""),
		GLenum.IN("transformType", "", TransformTypes),
		GLfloat_p.OUT("returnedSpacing", "")
	)

	ignoreMissing _ void(
		"GetPathColorGenivNV",
		"",

		this["PathColorGenNV"]["color"],
		GLenum.IN("pname", "", "#PATH_GEN_MODE_NV #PATH_GEN_COLOR_FORMAT_NV #PATH_GEN_COEFF_NV"),
		returnValue _ GLint_p.OUT("value", "")
	)

	ignoreMissing _ void(
		"GetPathColorGenfvNV",
		"",

		this["PathColorGenNV"]["color"],
		this["GetPathColorGenivNV"]["pname"],
		returnValue _ GLfloat_p.OUT("value", "")
	)

	ignoreMissing _ void(
		"GetPathTexGenivNV",
		"",

		GLenum.IN("texCoordSet", ""),
		GLenum.IN("pname", ""),
		returnValue _ GLint_p.OUT("value", "")
	)

	ignoreMissing _ void(
		"GetPathTexGenfvNV",
		"",

		GLenum.IN("texCoordSet", ""),
		GLenum.IN("pname", ""),
		returnValue _ GLfloat_p.OUT("value", "")
	)

	GLboolean(
		"IsPointInFillPathNV",
		"",

		GLuint.IN("path", ""),
		GLuint.IN("mask", ""),
		GLfloat.IN("x", ""),
		GLfloat.IN("y", "")
	)

	GLboolean(
		"IsPointInStrokePathNV",
		"",

		GLuint.IN("path", ""),
		GLfloat.IN("x", ""),
		GLfloat.IN("y", "")
	)

	GLfloat(
		"GetPathLengthNV",
		"",

		GLuint.IN("path", ""),
		GLsizei.IN("startSegment", ""),
		GLsizei.IN("numSegments", "")
	)

	GLboolean(
		"PointAlongPathNV",
		"",

		GLuint.IN("path", ""),
		GLsizei.IN("startSegment", ""),
		GLsizei.IN("numSegments", ""),
		GLfloat.IN("distance", ""),
		nullable _ Check(1) _ GLfloat_p.OUT("x", ""),
		nullable _ Check(1) _ GLfloat_p.OUT("y", ""),
		nullable _ Check(1) _ GLfloat_p.OUT("tangentX", ""),
		nullable _ Check(1) _ GLfloat_p.OUT("tangentY", "")
	)

	ignoreMissing _ void(
		"MatrixLoad3x2fNV",
		"",

		GLenum.IN("matrixMode", "", "#PATH_PROJECTION_NV #PATH_MODELVIEW_NV"),
		Check(3 * 2) _ const _ GLfloat_p.IN("m", "")
	)

	ignoreMissing _ void(
		"MatrixLoad3x3fNV",
		"",

		this["MatrixLoad3x2fNV"]["matrixMode"],
		Check(3 * 3) _ const _ GLfloat_p.IN("m", "")
	)

	ignoreMissing _ void(
		"MatrixLoadTranspose3x3fNV",
		"",

		this["MatrixLoad3x2fNV"]["matrixMode"],
		this["MatrixLoad3x3fNV"]["m"]
	)

	ignoreMissing _ void(
		"MatrixMult3x2fNV",
		"",

		this["MatrixLoad3x2fNV"]["matrixMode"],
		Check(3 * 2) _ const _ GLfloat_p.IN("m", "")
	)

	ignoreMissing _ void(
		"MatrixMult3x3fNV",
		"",

		this["MatrixLoad3x2fNV"]["matrixMode"],
		this["MatrixLoad3x3fNV"]["m"]
	)

	ignoreMissing _ void(
		"MatrixMultTranspose3x3fNV",
		"",

		this["MatrixLoad3x2fNV"]["matrixMode"],
		this["MatrixLoad3x3fNV"]["m"]
	)

	ignoreMissing _ void(
		"GetProgramResourcefvNV",
		"",

		GLuint.IN("program", ""),
		GLenum.IN("programInterface", ""),
		GLuint.IN("index", ""),
		AutoSize("props") _ GLsizei.IN("propCount", ""),
		const _ GLenum_p.IN("props", ""),
		AutoSize("params") _ GLsizei.IN("bufSize", ""),
		Check(1) _ nullable _ GLsizei_p.OUT("length", ""),
		Return("length") _ GLfloat_p.OUT("params", "")
	)
}
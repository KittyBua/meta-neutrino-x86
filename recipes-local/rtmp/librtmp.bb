DESCRIPTION = "librtmp Real-Time Messaging Protocol API"
LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=e344c8fa836c3a41c4cbd79d7bd3a379"

DEPENDS = "openssl zlib"

SRCREV = "${AUTOREV}"
PV = "${SRCPV}"

inherit pkgconfig

SRC_URI = "git://git.ffmpeg.org/rtmpdump;protocol=git"

S = "${WORKDIR}/git/librtmp"

do_compile() {
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CFLAGS="${CFLAGS} -fPIC" LDFLAGS="${LDFLAGS}"
}

do_install() {
	oe_runmake DESTDIR=${D} install
}

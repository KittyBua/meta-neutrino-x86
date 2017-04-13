DESCRIPTION = "ISS-Position Lua Plugin"
MAINTAINER = "theobald123"
LICENSE = "proprietary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/license;md5=17a6b3d5436a55985b200c725761907a"
HOMEPAGE = "http://www.dbox2world.net/board293-cst-coolstream/board313-cst-coolstream-downloads/board319-coolstream-plugins/12664-lua-iss-position/"

S = "${WORKDIR}"

PR = "r1"

SRC_URI = "file://iss-position.lua \
	   file://iss-position.cfg \
	   file://iss-position_hint.png \
	   file://license \
"

do_install () {
	install -d ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/iss-position.lua ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/iss-position.cfg ${D}/home/builder/.config/neutrino/plugins
	install -m 644 ${S}/iss-position_hint.png ${D}/home/builder/.config/neutrino/plugins
}

FILES_${PN} += "/home \
"

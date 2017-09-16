SUMMARY = "OSCam: Open Source Conditional Access Module"
HOMEPAGE = "http://www.streamboard.tv/oscam/"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "libusb1 openssl"

SRC_URI = "git://github.com/nx111/oscam.git;protocol=https \
		   file://oscam.conf \
		   file://oscam.server \
		   file://oscam.user \
		   file://oscam.service \
"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
	
INHIBIT_PACKAGE_STRIP = "1"

INITSCRIPT_NAME = "cam.sh"
INITSCRIPT_PARAMS = "defaults"

inherit cmake systemd

do_configure_append_coolstream-hd2 () {
	if [ ${BOXTYPE} = "kronos" ];then
		sed -i "s|^#define MAX_COOL_DMX.*|#define MAX_COOL_DMX 3|" ${S}/module-dvbapi-coolapi.c
	fi
}

EXTRA_OECMAKE = " \
		 -DCS_SVN_VERSION="${SRCPV}" \
		 -DDEFAULT_CS_CONFDIR="/etc" \
		 -DWEBIF=1 \
		 -DHAVEDVBAPI=1 \
		 -DUSE_LIBCRYPTO=1 \
		 -DUSE_LIBUSB=0 \
		 -DUSE_PCSC=0 \ 
		 -DUSE_STAPI=1 \
		 -DREADER_IRDETO=1 \
		 -DREADER_NAGRA=1 \
		 -DREADER_SECA=0 \
		 -DREADER_CRYPTOWORKS=0 \
		 -DREADER_CONAX=0 \
		 -DREADER_VIACCESS=0 \
		 -DREADER_VIDEOGUARD=0 \
		 -DMODULE_CAMD35=1 \
		 -DMODULE_GBOX=0 \
		 -DMODULE_CCCAM=1 \
		 -DMODULE_CCCSHARE=1 \
		 -DCARDREADER_SC8IN1=0 \
		 -DCARDREADER_SMARGO=0 \
"

EXTRA_OECMAKE_append_coolstream-hd1 += "-DOSCAM_SYSTEM_NAME=Coolstream \
"

EXTRA_OECMAKE_append_coolstream-hd2 += "-DOSCAM_SYSTEM_NAME=CST2 \
"

do_install () {
	install -d ${D}/usr/bin ${D}/etc
	install -D -m 755 ${WORKDIR}/build/oscam ${D}/usr/bin/oscam
	install -m 0644 ${WORKDIR}/oscam.conf ${D}/etc
	install -m 0644 ${WORKDIR}/oscam.server ${D}/etc
	install -m 0644 ${WORKDIR}/oscam.user ${D}/etc
	if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
  		install -d ${D}${systemd_unitdir}/system
  		install -m 0644 ${WORKDIR}/oscam.service ${D}${systemd_unitdir}/system/oscam.service
	fi
}

SYSTEMD_SERVICE_${PN} = "oscam.service"

INSANE_SKIP_${PN} = "already-stripped"
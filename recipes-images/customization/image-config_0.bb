SUMMARY = 'Customize stuff in the image without having to touch other packages'
LICENSE = "MIT"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit allarch

SRC_URI = " \
	file://COPYING.MIT \
	file://default.bootlogd \
	file://00aliases \
"

PR = "r1"

S = "${WORKDIR}"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

do_install() {
	install -m 0644 -D ${S}/default.bootlogd ${D}/${sysconfdir}/default/bootlogd
	install -m 0644 -D ${S}/00aliases        ${D}/${sysconfdir}/profile.d/00aliases
}

CONFFILES_$PN = " \
	${sysconfdir}/default/bootlogd \
"

DESCRIPTION = "A a package of utilities for doing and managing mounts of the Linux CIFS filesystem."
HOMEPAGE = "http://wiki.samba.org/index.php/LinuxCIFS_utils"

LICENSE = "GPLv3 & LGPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
DEPENDS = "samba"
PV = "6.5"
PR = "r1"

SRCREV = "7edaddd4d91cdb06a55f25fd66c609c4c43168f8"
SRC_URI = "git://git.samba.org/cifs-utils.git;branch=master"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= ""
PACKAGECONFIG[cap] = "--with-libcap,--without-libcap,libcap"
# when enabled, it creates ${bindir}/cifscreds and --ignore-fail-on-non-empty in do_install_append is needed
PACKAGECONFIG[cifscreds] = "--enable-cifscreds,--disable-cifscreds,keyutils"
# when enabled, it creates ${sbindir}/cifs.upcall and --ignore-fail-on-non-empty in do_install_append is needed
PACKAGECONFIG[cifsupcall] = "--enable-cifsupcall,--disable-cifsupcall,krb5 talloc keyutils"

inherit autotools-brokensep pkgconfig

do_install_append() {
    # Remove empty /usr/bin and /usr/sbin directories since the mount helper
    # is installed to /sbin
    rmdir --ignore-fail-on-non-empty ${D}${bindir} ${D}${sbindir}
}

RRECOMMENDS_${PN} = "kernel-module-cifs"

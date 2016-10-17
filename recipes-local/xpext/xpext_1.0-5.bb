LICENSE= "MIT"
DESCRIPTION = "X Server Nokia 770 extensions library"
SECTION = "x11/libs"
DEPENDS = "virtual/libx11 libxext"

LIC_FILES_CHKSUM = "file://COPYING;md5=db043791349ba57ad1169e1c92477cb6"

SRC_URI = "http://repository.maemo.org/pool/maemo/ossw/source/x/${PN}/${PN}_${PV}.tar.gz \
	   file://auxdir.patch \
	   file://dummy \
"

S = "${WORKDIR}/xpext-1.0"

inherit autotools-brokensep pkgconfig

do_configure_prepend () {
  cd ${S}
  chmod +x ./autogen.sh
  ./autogen.sh
}

ALLOW_EMPTY_${PN} = "1"

SRC_URI[md5sum] = "1b0cb67b6f2bd7c4abef17648b062896"
SRC_URI[sha256sum] = "a3b06f5188fd9effd0799ae31352b3cd65cb913b964e2c1a923ffa9d3c08abbe"



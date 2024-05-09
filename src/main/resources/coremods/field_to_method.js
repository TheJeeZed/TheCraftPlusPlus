var ASMAPI = Java.type('net.minecraftforge.coremod.api.ASMAPI')

function initializeCoreMod() {
    return {
        'copperbucketitem': {
            'target': {
                'type': 'CLASS',
                'name': 'net.thejeezed.craftplusplus.item.custom.item.CopperBucketItem'
            },
            'transformer': function(classNode) {
                ASMAPI.redirectFieldToMethod(classNode, 'content', 'getFluid')
                return classNode;
            }
        }
    }
}
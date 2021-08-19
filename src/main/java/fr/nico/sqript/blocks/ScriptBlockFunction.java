package fr.nico.sqript.blocks;

import fr.nico.sqript.compiling.ScriptException;
import fr.nico.sqript.compiling.ScriptToken;
import fr.nico.sqript.meta.Block;

@Block(name = "function",
        description = "Function blocks",
        examples = "function broadcast(message): #Sends a message to all players\n" +
                "    for p in all players:\n" +
                "        send message to p",
        regex = "^function .*")
public class ScriptBlockFunction extends ScriptFunctionalBlock {


    public ScriptBlockFunction(ScriptToken head) throws ScriptException {
        super(head);
    }

    @Override
    public void init(ScriptLineBlock block) throws Exception {
        super.init(block);
        setRoot(getMainField().compile());
    }
}
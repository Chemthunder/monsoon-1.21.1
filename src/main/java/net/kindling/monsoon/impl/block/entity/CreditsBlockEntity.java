package net.kindling.monsoon.impl.block.entity;

import net.kindling.monsoon.impl.game.util.GameUtils;
import net.kindling.monsoon.impl.index.MonsoonBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CreditsBlockEntity extends BlockEntity {
    @Nullable private Author author = Author.KINDLING;
    public int age = 0;

    public CreditsBlockEntity(BlockPos pos, BlockState state) {
        super(MonsoonBlockEntities.CREDITS, pos, state);
    }

    public void updateListeners() {
        this.markDirty();
        if (this.getWorld() != null) {
            this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        this.age++;
        if (this.age % 40 == 0) this.updateListeners();

        if (this.age > 0 && this.age % GameUtils.getInTicks(5, 0) == 0 && this.author != null) {
            this.setAuthor(Author.getNext(this.author));
        }
    }

    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        nbt.putString("Author", this.getAuthor().asString());
        nbt.putInt("Age", this.age);
    }

    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        if (nbt.contains("Author")) {
            this.setAuthor(Author.fromString(nbt.getString("Author")));
        }

        this.age = nbt.getInt("Age");
    }

    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    public Author getAuthor() {
        return this.author != null ? this.author : Author.KINDLING;
    }

    public void setAuthor(@Nullable Author author) {
        this.author = author != null ? author : Author.KINDLING;
        this.updateListeners();
    }

    public enum Author implements StringIdentifiable { // Total Order: Artist, Coder, Concepts, Founder, Shaders
        KINDLING("kindling", "KindlingCinder", "Artist, Concepts, Founder"),
        CHEMTHUNDER("chemthunder", "Chemthunder", "Coder, Concepts"),
        EVEREST("everest", "eeverest", "Coder, Concepts, Shaders"),
        ACOYT("acoyt", "AcoYT", "Artist, Coder, Concepts");

        private final String id;
        private final String name;
        private final String desc;

        Author(final String id, final String name, final String desc) {
            this.id = id;
            this.name = name;
            this.desc = desc;
        }

        public String asString() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public String getDesc() {
            return this.desc;
        }

        public static Author fromString(String name) {
            for (Author author : Author.values()) if (author.asString().equalsIgnoreCase(name)) return author;
            return KINDLING;
        }

        public static Author getNext(Author author) {
            Author[] values = Author.values();
            return values[(author.ordinal() + 1) % values.length];
        }
    }
}

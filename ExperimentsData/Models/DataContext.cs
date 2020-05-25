using ExperimentsData.Models.DAO;
using Microsoft.EntityFrameworkCore;

namespace ExperimentsData.Models
{
    public class DataContext : DbContext
    {
        public DataContext(DbContextOptions<DataContext> options) : base(options)
        {}
        public DbSet<DatasetEntity> Datasets { get; set; }
        public DbSet<SampleEntity> Samples { get; set; }
        public DbSet<AttributeEntity> Attributes { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<SampleEntity>()
                .HasAlternateKey(x => new {x.OriginalFileName, x.DatasetEntityId});
        }
        
    }
}
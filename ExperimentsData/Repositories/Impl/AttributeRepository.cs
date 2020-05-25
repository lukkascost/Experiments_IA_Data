using System;
using System.Collections.Generic;
using System.Linq;
using ExperimentsData.Models;
using ExperimentsData.Models.DAO;
using Microsoft.EntityFrameworkCore;

namespace ExperimentsData.Repositories.Impl
{
    public class AttributeRepository : IAttributeRepository
    {
        private DataContext _context;

        public AttributeRepository(DataContext context)
        {
            _context = context;
        }

        public List<AttributeEntity> GetAllBySampleId(Guid sampleId)
        {
            return _context.Attributes.Include(x => x.SampleEntity)
                .Where(x => x.SampleEntity.Id == sampleId).ToList();
        }

        public void Save(AttributeEntity entity)
        {
            _context.Attributes.Add(entity);
            _context.Entry(entity).State = EntityState.Added;
            _context.SaveChanges();
        }
    }
}